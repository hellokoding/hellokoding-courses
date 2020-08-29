package com.hellokoding.springboot.oauth2;

import com.github.scribejava.core.builder.ServiceBuilder;
import com.github.scribejava.core.builder.api.DefaultApi20;
import com.github.scribejava.core.oauth.OAuth20Service;
import lombok.RequiredArgsConstructor;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@RequiredArgsConstructor
@Component
public class OAuth2ServiceFactory {
    private static Map<String, OAuth20Service> services = new HashMap<>();
    private final Environment environment;
    private static final String CLIENT_ID = "oauth2.client.registration.%s.clientId";
    private static final String CLIENT_SECRET = "oauth2.client.registration.%s.clientSecret";
    private static final String REDIRECT_URI = "oauth2.client.registration.%s.redirectUri";
    private static final String SCOPE = "oauth2.client.registration.%s.scope";
    private static final String AUTHORIZATION_GRANT_TYPE = "oauth2.client.registration.%s.authorizationGrantType";

    public OAuth20Service getService(String serviceId) {
        if (services.containsKey(serviceId))
            return services.get(serviceId);

        String clientId = environment.getProperty(String.format(CLIENT_ID, serviceId));
        String clientSecret = environment.getProperty(String.format(CLIENT_SECRET, serviceId));
        String redirectUri = environment.getProperty(String.format(REDIRECT_URI, serviceId));
        String scope = environment.getProperty(String.format(SCOPE, serviceId));
        String authorizationGrantType = environment.getProperty(String.format(AUTHORIZATION_GRANT_TYPE, serviceId));
        OAuth20Service oAuth20Service = new ServiceBuilder(clientId)
            .apiSecret(clientSecret)
            .callback(redirectUri)
            .defaultScope(scope)
            .responseType(authorizationGrantType)
            .build(new OAuth2Api(serviceId));
        services.put(serviceId, oAuth20Service);

        return oAuth20Service;
    }

    @RequiredArgsConstructor
    class OAuth2Api extends DefaultApi20 {
        private final String serviceId;

        @Override
        public String getAccessTokenEndpoint() {
            return environment.getProperty(String.format("oauth2.client.provider.%s.tokenUri", serviceId));
        }

        @Override
        protected String getAuthorizationBaseUrl() {
            return environment.getProperty(String.format("oauth2.client.provider.%s.authorizationUri", serviceId));
        }

        public String getUserInfoEndpoint() {
            return environment.getProperty(String.format("oauth2.client.provider.%s.userInfoUri", serviceId));
        }
    }
}
