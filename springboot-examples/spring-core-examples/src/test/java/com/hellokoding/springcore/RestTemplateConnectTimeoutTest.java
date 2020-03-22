package com.hellokoding.springcore;

import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.web.client.RestTemplate;

import java.net.SocketTimeoutException;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.ThrowableAssert.catchThrowable;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = RestTemplateWithTimeoutConfig.class)
public class RestTemplateConnectTimeoutTest {
    @Autowired
    private RestTemplate restTemplateWithConnectTimeout;

    @ParameterizedTest
    @ValueSource(strings = {"http://example.com:81", "http://10.255.255.255"})
    public void testConnectTimeout(String url) {
        long startMillis = System.currentTimeMillis();

        Throwable throwable = catchThrowable(() -> {
            restTemplateWithConnectTimeout.getForObject(url, String.class);
        });

        long endMillis = System.currentTimeMillis();
        System.out.println("Execution time: " + (endMillis - startMillis));

        assertThat(throwable).hasRootCauseInstanceOf(SocketTimeoutException.class);
    }
}
