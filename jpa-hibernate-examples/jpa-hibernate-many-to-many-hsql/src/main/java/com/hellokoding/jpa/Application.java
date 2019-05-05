package com.hellokoding.jpa;

import com.hellokoding.jpa.model.Book;
import com.hellokoding.jpa.model.Publisher;
import com.hellokoding.jpa.repository.BookRepository;
import com.hellokoding.jpa.repository.PublisherRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.transaction.Transactional;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

@SpringBootApplication
public class Application implements CommandLineRunner {
    private static final Logger logger = LoggerFactory.getLogger(Application.class);

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private PublisherRepository publisherRepository;

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    @Override
    @Transactional
    public void run(String... strings) throws Exception {
        // save a couple of books
        Publisher publisherA = new Publisher("Publisher A");
        Publisher publisherB = new Publisher("Publisher B");
        Publisher publisherC = new Publisher("Publisher C");
        Book bookA = new Book("Book A", new HashSet<>(Arrays.asList(publisherA, publisherB)));
        Book bookB = new Book("Book B", new HashSet<>(Arrays.asList(publisherA, publisherC)));
        bookRepository.saveAll(Arrays.asList(bookA, bookB));

        // fetch all books
        for(Book book : bookRepository.findAll()) {
            logger.info(book.toString());
        }

        // save a couple of publishers
        Book bookD = new Book("Book D");
        Book bookE = new Book("Book E");
        Book bookF = new Book("Book F");
        Publisher publisherD = new Publisher("Publisher D", new HashSet<Book>(Arrays.asList(bookD, bookE)));
        Publisher publisherE = new Publisher("Publisher E", new HashSet<Book>(Arrays.asList(bookE, bookF)));
        publisherRepository.saveAll(Arrays.asList(publisherD, publisherE));

        // fetch all publishers
        for(Publisher publisher : publisherRepository.findAll()) {
            logger.info(publisher.toString());
        }
    }
}
