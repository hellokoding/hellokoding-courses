package com.hellokoding.util;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@Slf4j

@SpringBootApplication
public class Application {
    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    @Bean
    public CommandLineRunner runner(SimpleProperties simpleProperties, NestedProperties nestedProperties) {
        return r -> {
            log.info(simpleProperties.getA());
            log.info(nestedProperties.getA().getB());
        };
    }
}
