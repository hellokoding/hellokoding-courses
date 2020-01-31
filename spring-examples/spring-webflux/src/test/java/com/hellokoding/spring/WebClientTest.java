package com.hellokoding.spring;

import org.junit.jupiter.api.Test;
import org.springframework.http.HttpHeaders;
import org.springframework.web.reactive.function.client.ClientResponse;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import static org.assertj.core.api.Assertions.assertThat;

public class WebClientTest {
    @Test
    public void createWebClient() {
        WebClient webClient1 = WebClient.create();
        WebClient webClient2 = WebClient.create("http://localhost:8080");
    }

    @Test
    public void createWebClientWithBuilder() {
        WebClient webClient = WebClient.builder()
            .baseUrl("http://localhost:8080")
            .defaultHeader(HttpHeaders.USER_AGENT, "Hello Koding")
            .defaultCookie("cookie name", "cookie value")
            .build();
    }

    @Test
    public void buildAnHttpRequest() {
        WebClient webClient = WebClient.create("http://localhost:8080");

        Mono<String> result = webClient.get()
            .uri(uriBuilder -> uriBuilder
                .path("/hello")
                .queryParam("name", "Koding")
                .build())
            .retrieve()
            .bodyToMono(String.class);

        assertThat(result.block())
            .isEqualTo("Hello, Koding!");
    }

    @Test
    public void exchange() {
        WebClient webClient = WebClient.create("http://localhost:8080");

        Mono<ClientResponse> result = webClient.get()
            .uri(uriBuilder -> uriBuilder
                .path("/hello")
                .queryParam("name", "Koding")
                .build())
            .exchange();

        assertThat(result.flatMap(res -> res.bodyToMono(String.class)).block())
            .isEqualTo("Hello, Koding!");
    }
}
