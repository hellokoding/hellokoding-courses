package com.hellokoding.jpa;

import com.hellokoding.jpa.book.BookService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.persistence.*;

@SpringBootApplication
public class JpaApplication implements CommandLineRunner {
    private final BookService bookService;

    public JpaApplication(BookService bookService) {
        this.bookService = bookService;
    }

    @Override
    public void run(String... args) {
        bookService.create();
        bookService.read();
        bookService.delete();
    }

    public static void main(String[] args) {
        SpringApplication.run(JpaApplication.class, args);
    }

    @Entity
    public static class Book {
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private int id;

        private String name;

        @ManyToOne(fetch = FetchType.EAGER)
        @JoinColumn(name = "book_category_id", referencedColumnName = "id")
        private BookCategory bookCategory;

        public Book() {
        }

        public Book(String name) {
            this.name = name;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public BookCategory getBookCategory() {
            return bookCategory;
        }

        public void setBookCategory(BookCategory bookCategory) {
            this.bookCategory = bookCategory;
            bookCategory.getBooks().add(this);
        }
    }
}
