package com.hellokoding.springcore;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.web.client.RestTemplate;

import java.net.SocketTimeoutException;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.ThrowableAssert.catchThrowable;

@SpringBootTest(
    webEnvironment = WebEnvironment.RANDOM_PORT,
    classes = {RestTemplateWithTimeoutConfig.class, RestTemplateReadTimeoutApplication.class}
)
public class RestTemplateReadTimeoutTest {
    @LocalServerPort
    private int port;

    @Autowired
    private RestTemplate restTemplateWithConnectTimeout;

    @Autowired
    private RestTemplate restTemplateWithConnectReadTimeout;

    @Test
    public void testReadTimeout() {
        long startMillis = System.currentTimeMillis();

        Throwable throwable = catchThrowable(() -> {
            String url = String.format("http://localhost:%d/test/delay?millis=%d", port, 600);
            restTemplateWithConnectReadTimeout.getForObject(url, String.class);
        });

        long endMillis = System.currentTimeMillis();
        System.out.println("Execution time: " + (endMillis - startMillis));

        assertThat(throwable).hasRootCauseInstanceOf(SocketTimeoutException.class);
    }

    @Test
    public void testReadTimeout2() {
        long startMillis = System.currentTimeMillis();

        Throwable throwable = catchThrowable(() -> {
            String url = String.format("http://localhost:%d/test/delay?millis=%d", port, 600);
            restTemplateWithConnectTimeout.getForObject(url, String.class);
        });

        long endMillis = System.currentTimeMillis();
        System.out.println("Execution time: " + (endMillis - startMillis));

        assertThat(throwable).hasRootCauseInstanceOf(SocketTimeoutException.class);
    }
}
