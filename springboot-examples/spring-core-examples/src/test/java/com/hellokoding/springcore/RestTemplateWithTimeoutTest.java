package com.hellokoding.springcore;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.web.client.RestTemplate;

import java.net.SocketTimeoutException;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

@SpringBootTest(
    webEnvironment = WebEnvironment.RANDOM_PORT,
    classes = {RestTemplateWithTimeoutConfig.class, RestTemplateWithTimeoutTestApplication.class}
)
public class RestTemplateWithTimeoutTest {
    @LocalServerPort
    private int port;

    @Autowired
    private RestTemplate restTemplateWithConnectReadTimeout;

    @Autowired
    private RestTemplate restTemplateWithConnectTimeout;

    @Test
    public void testConnectTimeout() {
        assertThatThrownBy(() -> {
            String url = String.format("http://localhost:%d/test/delay?millis=%d", port, 600);
            restTemplateWithConnectTimeout.getForObject(url, String.class);
        }).hasRootCauseInstanceOf(SocketTimeoutException.class);
    }

    @Test
    public void testConnectReadTimeout() {
        assertThatThrownBy(() -> {
            String url = String.format("http://localhost:%d/test/delay?millis=%d", port, 600);
            restTemplateWithConnectReadTimeout.getForObject(url, String.class);
        }).hasRootCauseInstanceOf(SocketTimeoutException.class);
    }
}
