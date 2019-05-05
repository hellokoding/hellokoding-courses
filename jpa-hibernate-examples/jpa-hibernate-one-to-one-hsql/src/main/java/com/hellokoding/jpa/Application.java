package com.hellokoding.jpa;

import com.hellokoding.jpa.model.Book;
import com.hellokoding.jpa.model.BookDetail;
import com.hellokoding.jpa.repository.BookRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.ArrayList;
import java.util.List;

@SpringBootApplication
public class Application implements CommandLineRunner {
    private static final Logger logger = LoggerFactory.getLogger(Application.class);

    @Autowired
    private BookRepository bookRepository;

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    @Override
    public void run(String... strings) throws Exception {
        // save a couple of books
        List<Book> books = new ArrayList<>();
        books.add(new Book("Book A", new BookDetail(49)));
        books.add(new Book("Book B", new BookDetail(59)));
        books.add(new Book("Book C", new BookDetail(69)));
        bookRepository.saveAll(books);

        // fetch all books
        for (Book book : bookRepository.findAll()) {
            logger.info(book.toString());
        }
    }
}
