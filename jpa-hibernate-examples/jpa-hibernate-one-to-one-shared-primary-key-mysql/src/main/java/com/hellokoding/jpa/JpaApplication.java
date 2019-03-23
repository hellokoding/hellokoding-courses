package com.hellokoding.jpa;

import com.hellokoding.jpa.book.Book;
import com.hellokoding.jpa.book.BookDetail;
import com.hellokoding.jpa.book.BookRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@RequiredArgsConstructor
@SpringBootApplication
public class JpaApplication implements CommandLineRunner {
    private final BookRepository bookRepository;

    public static void main(String[] args) {
        SpringApplication.run(JpaApplication.class, args);
    }

    @Override
    public void run(String... args) {
        // Create a couple of Book and BookDetail
        bookRepository.save(new Book("Hello Koding 1", new BookDetail(101)));
        bookRepository.save(new Book("Hello Koding 2", new BookDetail(102)));
    }
}
