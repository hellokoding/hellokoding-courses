package com.hellokoding.jpa;

import com.hellokoding.jpa.model.Book;
import com.hellokoding.jpa.model.BookPublisher;
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
import java.util.Date;

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
        // create new
        Book bookA = new Book("Book A");
        Publisher publisherA = new Publisher("Publisher A");

        BookPublisher bookPublisher = new BookPublisher();
        bookPublisher.setBook(bookA);
        bookPublisher.setPublisher(publisherA);
        bookPublisher.setPublishedDate(new Date());

        bookA.getBookPublishers().add(bookPublisher);

        publisherRepository.save(publisherA);
        bookRepository.save(bookA);

        // test
        System.out.println(bookA.getBookPublishers().size());

        // update
        bookA.getBookPublishers().remove(bookPublisher);
        bookRepository.save(bookA);

        // test
        System.out.println(bookA.getBookPublishers().size());
    }
}
