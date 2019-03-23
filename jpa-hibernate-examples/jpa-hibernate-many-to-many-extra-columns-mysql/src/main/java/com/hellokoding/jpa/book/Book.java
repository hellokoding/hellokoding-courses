package com.hellokoding.jpa.book;

import lombok.Data;

import javax.persistence.*;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Data

@Entity
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "name")
    private String name;

    @OneToMany(mappedBy = "book", cascade = CascadeType.ALL)
    private Set<BookPublisher> bookPublishers;

    public Book(String name, BookPublisher... bookPublishers) {
        this.name = name;
        for(BookPublisher bookPublisher : bookPublishers) bookPublisher.setBook(this);
        this.bookPublishers = Stream.of(bookPublishers).collect(Collectors.toSet());
    }
}
