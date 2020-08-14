package com.hellokoding.springcore;

import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;

import java.time.Duration;

@Configuration
public class RestTemplateWithTimeoutConfig {
    static final int TIMEOUT = 500;

    @Bean
    RestTemplate restTemplateWithConnectReadTimeout() {
        return new RestTemplateBuilder()
            .setConnectTimeout(Duration.ofMillis(TIMEOUT))
            .setReadTimeout(Duration.ofMillis(TIMEOUT))
            .build();
    }

    @Bean
    RestTemplate restTemplateWithConnectTimeout() {
        return new RestTemplateBuilder()
            .setConnectTimeout(Duration.ofMillis(TIMEOUT))
            .build();
    }

    @Bean
    RestTemplate restTemplateTimeoutWithRequestFactory() {
        SimpleClientHttpRequestFactory requestFactory = new SimpleClientHttpRequestFactory();
        requestFactory.setConnectTimeout(TIMEOUT);
        requestFactory.setReadTimeout(TIMEOUT);

        return new RestTemplate(requestFactory);
    }
}
