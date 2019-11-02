package com.hellokoding.springcore;

import org.junit.Before;
import org.junit.Test;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.web.client.MockRestServiceServer;
import org.springframework.web.client.RestClientResponseException;
import org.springframework.web.client.RestTemplate;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.method;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.requestTo;
import static org.springframework.test.web.client.response.MockRestResponseCreators.withBadRequest;
import static org.springframework.test.web.client.response.MockRestResponseCreators.withServerError;

public class RestClientResponseExceptionTest {
    private MockRestServiceServer mockRestServiceServer;
    private RestTemplate restTemplate;

    @Before
    public void setUp() {
        restTemplate = new RestTemplate();
        mockRestServiceServer = MockRestServiceServer.createServer(restTemplate);
    }

    @Test
    public void response4xx() {
        String url = "/server/products/1";
        mockRestServiceServer
            .expect(requestTo(url))
            .andExpect(method(HttpMethod.GET))
            .andRespond(withBadRequest());

        ResponseEntity responseEntity = consumeWebService(url, Object.class);

        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.BAD_REQUEST);
    }

    @Test
    public void response5xx() {
        String url = "/server/products/1";
        mockRestServiceServer
            .expect(requestTo(url))
            .andExpect(method(HttpMethod.GET))
            .andRespond(withServerError());

        ResponseEntity responseEntity = consumeWebService(url, Object.class);

        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.INTERNAL_SERVER_ERROR);
    }

    <T> ResponseEntity consumeWebService(String url, Class<T> responseType) {
        try {
            return restTemplate.getForEntity(url, responseType);
        } catch (RestClientResponseException e) {
            return ResponseEntity
                .status(e.getRawStatusCode())
                .body(e.getResponseBodyAsString());
        }
    }
}
