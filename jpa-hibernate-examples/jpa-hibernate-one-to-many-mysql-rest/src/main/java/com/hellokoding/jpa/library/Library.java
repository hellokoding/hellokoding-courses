package com.hellokoding.jpa.library;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.hellokoding.jpa.book.Book;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.HashSet;
import java.util.Set;

@Entity
public class Library {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @NotNull
    private String name;

    @OneToMany(mappedBy = "library")
    @JsonIgnore
    private Set<Book> books;

    public Library() {
        books = new HashSet<>();
    }

    public Library(String name) {
        this.name = name;
        books = new HashSet<>();
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

    public Set<Book> getBooks() {
        return books;
    }

    public void setBooks(Set<Book> books) {
        this.books = books;
        for (Book book : books) {
            book.setLibrary(this);
        }
    }
}
