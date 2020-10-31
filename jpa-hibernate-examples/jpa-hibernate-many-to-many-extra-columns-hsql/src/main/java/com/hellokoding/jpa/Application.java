package com.hellokoding.jpa;

import com.hellokoding.jpa.model.Book;
import com.hellokoding.jpa.model.BookPublisher;
import com.hellokoding.jpa.model.Publisher;
import com.hellokoding.jpa.repository.BookPublisherRepository;
import com.hellokoding.jpa.repository.BookRepository;
import com.hellokoding.jpa.repository.PublisherRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.transaction.Transactional;
import java.util.Arrays;
import java.util.Date;

@RequiredArgsConstructor
@SpringBootApplication
public class Application implements CommandLineRunner {
    private final BookRepository bookRepository;
    private final PublisherRepository publisherRepository;
    private final BookPublisherRepository bookPublisherRepository;

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    @Override
    @Transactional
    public void run(String... strings) throws Exception {
        Book b1 = new Book("Spring Boot");
        Book b2 = new Book("Spring Data JPA");
        bookRepository.saveAll(Arrays.asList(b1, b2));

        Publisher p1 = new Publisher("HelloKoding 1");
        Publisher p2 = new Publisher("HelloKoding 2");
        publisherRepository.saveAll(Arrays.asList(p1, p2));

        BookPublisher bp1 = new BookPublisher(b1, p1, new Date());
        BookPublisher bp2 = new BookPublisher(b1, p2, new Date());
        BookPublisher bp3 = new BookPublisher(b2, p1, new Date());
        BookPublisher bp4 = new BookPublisher(b2, p2, new Date());
        bookPublisherRepository.saveAll(Arrays.asList(bp1, bp2, bp3, bp4));
    }
}
