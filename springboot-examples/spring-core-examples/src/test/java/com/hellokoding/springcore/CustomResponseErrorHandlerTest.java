package com.hellokoding.springcore;

import org.junit.Before;
import org.junit.Test;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.test.web.client.MockRestServiceServer;
import org.springframework.web.client.RestTemplate;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.method;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.requestTo;
import static org.springframework.test.web.client.response.MockRestResponseCreators.withBadRequest;
import static org.springframework.test.web.client.response.MockRestResponseCreators.withServerError;

public class CustomResponseErrorHandlerTest {
    private MockRestServiceServer mockRestServiceServer;
    private RestTemplate restTemplate;

    @Before
    public void setUp() {
        restTemplate = new RestTemplate();
        restTemplate.setErrorHandler(new CustomResponseErrorHandler());

        mockRestServiceServer = MockRestServiceServer.createServer(restTemplate);
    }

    @Test
    public void response4xx() {
        String url = "/server/products/1";
        mockRestServiceServer
            .expect(requestTo(url))
            .andExpect(method(HttpMethod.GET))
            .andRespond(withBadRequest());

        assertThatThrownBy(() -> consumeWebService(url, Object.class))
            .hasCauseInstanceOf(CustomException.class);
    }

    @Test
    public void response5xx() {
        String url = "/server/products/1";
        mockRestServiceServer
            .expect(requestTo(url))
            .andExpect(method(HttpMethod.GET))
            .andRespond(withServerError());

        assertThatThrownBy(() -> consumeWebService(url, Object.class))
            .hasCauseInstanceOf(CustomException.class);
    }

    <T> ResponseEntity consumeWebService(String url, Class<T> responseType) {
        return restTemplate.getForEntity(url, responseType);
    }
}
