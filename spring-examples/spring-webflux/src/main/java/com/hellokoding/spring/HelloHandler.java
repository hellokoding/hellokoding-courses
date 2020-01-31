package com.hellokoding.spring;

import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

@Component
public class HelloHandler {
    public Mono<ServerResponse> hello(ServerRequest request) {
        String name = request
            .queryParam("name")
            .orElse("Spring WebFlux");

        return ServerResponse.ok()
            .contentType(MediaType.TEXT_PLAIN)
            .bodyValue(String.format("Hello, %s!", name));
    }
}
