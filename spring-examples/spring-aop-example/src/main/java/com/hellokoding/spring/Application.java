package com.hellokoding.spring;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class Application {
    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    @Bean
    @LogExecutionTime
    public CommandLineRunner commandLineRunner(ApplicationContext ctx) {
        return args -> {
            Thread.sleep(100);
        };
    }
}
