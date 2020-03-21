package com.hellokoding.springcore;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class ConsumerService {
    @Autowired
    RestTemplate restTemplateWithErrorHandler;

    public <T> ResponseEntity consume(String url, Class<T> responseType) {
        return restTemplateWithErrorHandler.getForEntity(url, responseType);
    }
}
