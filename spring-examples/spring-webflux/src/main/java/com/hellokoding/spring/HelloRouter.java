package com.hellokoding.spring;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;

import static org.springframework.web.reactive.function.server.RequestPredicates.GET;
import static org.springframework.web.reactive.function.server.RequestPredicates.accept;

@Configuration
public class HelloRouter {
    @Bean
    public RouterFunction<ServerResponse> route(HelloHandler helloHandler) {
        return RouterFunctions
            .route(GET("/hello").and(accept(MediaType.TEXT_PLAIN)), helloHandler::hello);
    }
}
