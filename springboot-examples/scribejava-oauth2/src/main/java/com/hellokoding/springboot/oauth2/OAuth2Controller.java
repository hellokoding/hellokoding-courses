package com.hellokoding.springboot.oauth2;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.scribejava.core.model.OAuth2AccessToken;
import com.github.scribejava.core.model.OAuthRequest;
import com.github.scribejava.core.model.Verb;
import com.github.scribejava.core.oauth.OAuth20Service;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.view.RedirectView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Collections;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ExecutionException;

@RestController
@RequiredArgsConstructor
public class OAuth2Controller {
    private final OAuth2ServiceFactory oAuth2ServiceFactory;
    private final ObjectMapper objectMapper;

    @GetMapping("/oauth2/authorization/{serviceId}")
    public RedirectView oauth2Login(@PathVariable String serviceId, HttpServletRequest request) {
        String state = UUID.randomUUID().toString();
        request.getSession().setAttribute("state", state);
        return new RedirectView(oAuth2ServiceFactory.getService(serviceId).getAuthorizationUrl(state));
    }

    @GetMapping("/login/oauth2/code/{serviceId}")
    public RedirectView oauth2Code(@PathVariable String serviceId, String code, String state, HttpServletRequest request, HttpServletResponse response) throws InterruptedException, ExecutionException, IOException {
        if (!request.getSession().getAttribute("state").equals(state)) {
            response.setStatus(HttpStatus.UNAUTHORIZED.value());
        } else {
            OAuth20Service oAuth20Service = oAuth2ServiceFactory.getService(serviceId);
            OAuth2AccessToken accessToken = oAuth20Service.getAccessToken(code);

            OAuth2ServiceFactory.OAuth2Api oAuth2Api = (OAuth2ServiceFactory.OAuth2Api)oAuth20Service.getApi();
            final OAuthRequest oAuthRequest = new OAuthRequest(Verb.GET, oAuth2Api.getUserInfoEndpoint());
            oAuth20Service.signRequest(accessToken, oAuthRequest);
            Map<String, String> map = objectMapper.readValue(oAuth20Service.execute(oAuthRequest).getBody(), Map.class);
            request.getSession().setAttribute("user", map);
        }

        return new RedirectView("/");
    }

    @GetMapping("/user")
    public Map<String, Object> user(HttpServletRequest request) {
        Map<String, String> map = (Map)request.getSession().getAttribute("user");
        return Collections.singletonMap("name", map.get("name"));
    }
}
