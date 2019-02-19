package com.hellokoding.springboot.jpa;

import com.hellokoding.springboot.jpa.book.Book;
import com.hellokoding.springboot.jpa.book.BookRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.Arrays;
import java.util.Date;

@Slf4j

@SpringBootApplication
class JpaApplication {
    public static void main(String[] args) {
        SpringApplication.run(JpaApplication.class, args);
    }

    @Bean
    public CommandLineRunner runner(BookRepository bookRepository) {
        return r -> {
            // Create a couple of Book
            bookRepository.saveAll(Arrays.asList(new Book("Hello Koding 1", new Date()), new Book("Hello Koding 2", new Date())));

            // Fetch all
            log.info("My books: " + bookRepository.findAll());
        };
    }
}
