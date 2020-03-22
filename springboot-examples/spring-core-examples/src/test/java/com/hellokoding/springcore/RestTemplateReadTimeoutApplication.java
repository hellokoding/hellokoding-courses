package com.hellokoding.springcore;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
public class RestTemplateReadTimeoutApplication {
    public static void main(String[] args) {
        SpringApplication.run(RestTemplateReadTimeoutApplication.class, args);
    }

    @GetMapping("/test/delay")
    public ResponseEntity delay(int millis) throws InterruptedException {
        Thread.sleep(millis);

        return ResponseEntity.ok().build();
    }
}
