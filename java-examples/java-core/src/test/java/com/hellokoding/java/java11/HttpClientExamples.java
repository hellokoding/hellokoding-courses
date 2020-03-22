package com.hellokoding.java.java11;

import org.junit.Test;

import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLParameters;
import java.net.*;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.security.NoSuchAlgorithmException;
import java.time.Duration;
import java.util.concurrent.Executors;

import static org.assertj.core.api.Assertions.assertThat;

public class HttpClientExamples {
    @Test
    public void getAsync() {
        HttpClient client = HttpClient.newHttpClient();

        HttpRequest request = HttpRequest.newBuilder()
            .uri(URI.create("https://example.com/"))
            .build();

        client.sendAsync(request, HttpResponse.BodyHandlers.ofString())
            .thenApplyAsync(HttpResponse::body)
            .thenAcceptAsync(System.out::println)
            .join();
    }

    @Test
    public void createAnHTTPClient() throws NoSuchAlgorithmException {
        HttpClient client = HttpClient.newBuilder()
            .version(HttpClient.Version.HTTP_2)
            .proxy(ProxySelector.getDefault())
            .followRedirects(HttpClient.Redirect.NEVER)
            .authenticator(Authenticator.getDefault())
            .connectTimeout(Duration.ofSeconds(1))
            .cookieHandler(CookieHandler.getDefault())
            .executor(Executors.newFixedThreadPool(2))
            .priority(1)
            .sslContext(SSLContext.getDefault())
            .sslParameters(new SSLParameters())
            .build();
    }

    @Test
    public void createADefaultHTTPClient() {
        HttpClient client = HttpClient.newHttpClient();

        assertThat(client.version()).isEqualTo(HttpClient.Version.HTTP_2);
        assertThat(client.proxy()).isEmpty();
        assertThat(client.connectTimeout()).isEmpty();
        assertThat(client.followRedirects()).isEqualTo(HttpClient.Redirect.NEVER);
    }

    @Test
    public void buildAnHTTPRequest() {
        HttpRequest request = HttpRequest.newBuilder()
            .uri(URI.create("https://example.com/"))
            .POST(HttpRequest.BodyPublishers.noBody())
            .version(HttpClient.Version.HTTP_2)
            .header("key", "value")
            .build();
    }

    @Test
    public void retrieveAnHTTPResponse() {
        HttpClient client = HttpClient.newHttpClient();

        HttpRequest request = HttpRequest.newBuilder()
            .uri(URI.create("https://example.com/"))
            .POST(HttpRequest.BodyPublishers.ofString("ping!"))
            .build();

        client.sendAsync(request, HttpResponse.BodyHandlers.ofString())
            .thenApplyAsync(HttpResponse::headers)
            .thenAcceptAsync(System.out::println)
            .join();
    }
}
