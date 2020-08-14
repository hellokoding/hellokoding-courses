package com.hellokoding.jpa;

import com.hellokoding.jpa.book.*;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Arrays;
import java.util.Date;

@RequiredArgsConstructor
@SpringBootApplication
public class JpaApplication implements CommandLineRunner {
    private final BookRepository bookRepository;
    private final PublisherRepository publisherRepository;

    public static void main(String[] args) {
        SpringApplication.run(JpaApplication.class, args);
    }

    @Override
    public void run(String... args) {
        // Create a couple of Book, Publisher and BookPublisher
        Publisher publisherA = new Publisher("Publisher A");
        Publisher publisherB = new Publisher("Publisher B");
        publisherRepository.saveAll(Arrays.asList(publisherA, publisherB));

        bookRepository.save(new Book("Book 1", new BookPublisher(publisherA, new Date()), new BookPublisher(publisherB, new Date())));
        bookRepository.save(new Book("Book 2", new BookPublisher(publisherA, new Date())));
    }
}
