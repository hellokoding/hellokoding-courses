package com.hellokoding.jpa;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.stream.Collectors;
import java.util.stream.Stream;

@SpringBootApplication
class Application {
    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    @Bean
    public CommandLineRunner runner(EmployeeRepository employeeRepository) {
        return r -> {
            employeeRepository.save(new Employee("tom", Stream.of(
                new EmployeePhone("012", true),
                new EmployeePhone("013", false)
            ).collect(Collectors.toSet())));
        };
    }
}
