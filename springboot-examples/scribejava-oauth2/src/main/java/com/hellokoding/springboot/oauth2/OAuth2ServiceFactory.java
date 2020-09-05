package com.hellokoding.springboot.oauth2;

import com.github.scribejava.core.builder.ServiceBuilder;
import com.github.scribejava.core.builder.api.DefaultApi20;
import com.github.scribejava.core.extractors.OAuth2AccessTokenExtractor;
import com.github.scribejava.core.extractors.OAuth2AccessTokenJsonExtractor;
import com.github.scribejava.core.extractors.TokenExtractor;
import com.github.scribejava.core.model.OAuth2AccessToken;
import com.github.scribejava.core.oauth.OAuth20Service;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@RequiredArgsConstructor
@Component
public class OAuth2ServiceFactory {
    private static Map<String, OAuth20Service> services = new HashMap<>();
    private final OAuth2Properties oAuth2Properties;

    public OAuth20Service getService(String serviceId) {
        if (services.containsKey(serviceId))
            return services.get(serviceId);

        OAuth2Properties.Registration registration = oAuth2Properties.getRegistration().get(serviceId);
        OAuth2Properties.Provider provider = oAuth2Properties.getProvider().get(serviceId);

        OAuth20Service oAuth20Service = new ServiceBuilder(registration.getClientId())
            .apiSecret(registration.getClientSecret())
            .callback(registration.getRedirectUri())
            .defaultScope(registration.getScope())
            .responseType(registration.getAuthorizationGrantType())
            .userAgent("HelloKoding")
            .build(new OAuth2Api(provider));
        services.put(serviceId, oAuth20Service);

        return oAuth20Service;
    }

    @RequiredArgsConstructor
    class OAuth2Api extends DefaultApi20 {
        private final OAuth2Properties.Provider provider;

        @Override
        public String getAccessTokenEndpoint() {
            return provider.getTokenUri();
        }

        @Override
        public String getAuthorizationBaseUrl() {
            return provider.getAuthorizationUri();
        }

        @Override
        public String getRevokeTokenEndpoint() {
            return provider.getRevokeTokenUri();
        }

        @Override
        public TokenExtractor<OAuth2AccessToken> getAccessTokenExtractor() {
            if ("github".equalsIgnoreCase(provider.getName()))
                return OAuth2AccessTokenExtractor.instance();

            return OAuth2AccessTokenJsonExtractor.instance();
        }

        public String getUserInfoEndpoint() {
            return provider.getUserInfoUri();
        }

        public String getUserNameAttribute() {
            return provider.getUserNameAttribute();
        }

        public String getRevokePermissionEndpoint() {
            return provider.getRevokePermissionUri();
        }
    }
}
