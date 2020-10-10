package com.hellokoding.jpa.book;

import lombok.Data;

import javax.persistence.*;

@Data

@Entity
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "name")
    private String name;

    public Book(String name, BookPublisher... bookPublishers) {
        this.name = name;
        for(BookPublisher bookPublisher : bookPublishers) bookPublisher.setBook(this);
    }
}
