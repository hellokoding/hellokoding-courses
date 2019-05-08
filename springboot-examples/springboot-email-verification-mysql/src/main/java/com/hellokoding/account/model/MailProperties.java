package com.hellokoding.account.model;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "mail")
public class MailProperties {
    public static class SMTP {
        String host;
        String port;
        String username;
        String password;

        public String getHost() {
            return host;
        }

        public void setHost(String host) {
            this.host = host;
        }

        public String getPort() {
            return port;
        }

        public void setPort(String port) {
            this.port = port;
        }

        public String getUsername() {
            return username;
        }

        public void setUsername(String username) {
            this.username = username;
        }

        public String getPassword() {
            return password;
        }

        public void setPassword(String password) {
            this.password = password;
        }
    }

    private SMTP smtp;
    private String from;
    private String fromName;
    private String verificationapi;

    public SMTP getSmtp() {
        return smtp;
    }

    public void setSmtp(SMTP smtp) {
        this.smtp = smtp;
    }

    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public String getFromName() {
        return fromName;
    }

    public void setFromName(String fromName) {
        this.fromName = fromName;
    }

    public String getVerificationapi() {
        return verificationapi;
    }

    public void setVerificationapi(String verificationapi) {
        this.verificationapi = verificationapi;
    }
}
