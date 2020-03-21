package com.hellokoding.springcore;

import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class RestTemplateWithErrorHandlerConfig {
    @Bean
    RestTemplate restTemplateWithErrorHandler() {
        return new RestTemplateBuilder()
            .errorHandler(new CustomResponseErrorHandler())
            .build();
    }
}
