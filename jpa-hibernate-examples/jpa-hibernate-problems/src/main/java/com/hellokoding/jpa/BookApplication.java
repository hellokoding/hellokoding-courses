package com.hellokoding.jpa;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import javax.persistence.*;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import static javax.persistence.CascadeType.*;
import static javax.persistence.FetchType.*;

@Entity @Builder @AllArgsConstructor @NoArgsConstructor
class Book {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY) int id;
    String name;
    @ManyToOne(fetch = EAGER) BookCategory cat;
    //@OneToOne(cascade = PERSIST) BookDetail detail;
}

//@Entity @Builder @AllArgsConstructor @NoArgsConstructor
//class BookDetail {
//    @Id @MapsId("book_id") int id;
//    int numberOfPages;
//}

@Entity @Builder @AllArgsConstructor @NoArgsConstructor
class BookCategory {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY) int id;
    String name;
    @OneToMany(mappedBy = "cat", cascade = PERSIST, fetch = EAGER)
    @Builder.Default Set<Book> books = new HashSet<>();
}

interface BookCategoryRepository extends JpaRepository<BookCategory, Integer> {
    List<BookCategory> findFirst10ByOrderByName();
}

interface BookRepository extends JpaRepository<Book, Integer>{
    //@EntityGraph(attributePaths = "bookCategory")
    List<Book> findFirst10ByOrderByNameAsc();
}

@Service @RequiredArgsConstructor
class BookService {
    private final BookCategoryRepository bookCategoryRepository;
    private final BookRepository bookRepository;

    public void create() {
        Stream<BookCategory> bookCategories = IntStream.range(1, 11).mapToObj(i -> {
            BookCategory bookCategory = BookCategory.builder().name("Cat " + i).build();
            IntStream.range(1, 3).forEach(j -> {
                Book book = Book.builder()
                    .name(String.format("Book %d.%d", i, j))
                    .cat(bookCategory)
                    //.detail(BookDetail.builder().numberOfPages(i*100).build())
                    .build();

                bookCategory.books.add(book);
            });

            return bookCategory;
        });

        bookCategoryRepository.saveAll(bookCategories.collect(Collectors.toList()));
    }

    public void findCategories() {
        bookCategoryRepository.findFirst10ByOrderByName().forEach(c -> {
            System.out.println(c.name);
            c.books.forEach(b -> {
                System.out.println(b.name + " " + b.cat.name);
            });
        });
    }

    public List<String> findBookNames() {
        List<Book> books = bookRepository.findFirst10ByOrderByNameAsc();

        books.forEach(b -> {
            System.out.println(b.name + " " + b.cat.name);
        });

        return books.stream()
            .map(b -> b.name)
            .collect(Collectors.toList());
    }
}

@SpringBootApplication @RequiredArgsConstructor
public class BookApplication implements CommandLineRunner {
    private final BookService bookService;

    @Override
    public void run(String... args) {
        bookService.create();
        bookService.findCategories();
        //bookService.findBookNames().forEach(System.out::println);
    }

    public static void main(String[] args) {
        SpringApplication.run(BookApplication.class, args);
    }
}
