package com.hellokoding.springboot.oauth2;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
@ConfigurationProperties(prefix = "oauth2.client")
@Getter
public class OAuth2Properties {
    private final Map<String, Registration> registration = new HashMap<>();
    private final Map<String, Provider> provider = new HashMap<>();

    @Getter @Setter
    public static class Registration {
        private String clientId;
        private String clientSecret;
        private String redirectUri;
        private String scope;
        private String authorizationGrantType = "code";
    }

    @Getter @Setter
    public static class Provider {
        private String name;
        private String authorizationUri;
        private String tokenUri;
        private String userInfoUri;
        private String revokeTokenUri;
        private String revokePermissionUri;
        private String userNameAttribute = "name";
    }
}
