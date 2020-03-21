package com.hellokoding.springcore;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.client.MockRestServiceServer;
import org.springframework.test.web.client.ResponseActions;

import static com.hellokoding.springcore.CustomResponseErrorHandler.CustomException;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.method;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.requestTo;
import static org.springframework.test.web.client.response.MockRestResponseCreators.withBadRequest;
import static org.springframework.test.web.client.response.MockRestResponseCreators.withServerError;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = {RestTemplateWithErrorHandlerConfig.class, ConsumerService.class})
public class CustomResponseErrorHandlerTest {
    private static final String URL = "/server/products/1";
    private ResponseActions responseActions;

    @Autowired
    private ConsumerService consumerService;

    @BeforeEach
    public void setUp() {
        responseActions = MockRestServiceServer.createServer(consumerService.restTemplateWithErrorHandler)
            .expect(requestTo(URL))
            .andExpect(method(HttpMethod.GET));
    }

    @Test
    public void response4xx() {
        responseActions.andRespond(withBadRequest());

        assertThatThrownBy(() -> consumerService.consume(URL, Object.class))
            .hasCauseInstanceOf(CustomException.class);
    }

    @Test
    public void response5xx() {
        responseActions.andRespond(withServerError());

        assertThatThrownBy(() ->  consumerService.consume(URL, Object.class))
            .hasCauseInstanceOf(CustomException.class);
    }
}
