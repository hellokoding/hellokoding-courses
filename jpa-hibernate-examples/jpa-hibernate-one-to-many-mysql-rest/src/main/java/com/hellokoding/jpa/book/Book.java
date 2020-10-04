package com.hellokoding.jpa.book;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.hellokoding.jpa.library.Library;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @NotNull
    private String name;

    @NotNull
    @ManyToOne
    @JsonIgnoreProperties("books")
    private Library library;

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

    public Library getLibrary() {
        return library;
    }

    public void setLibrary(Library library) {
        this.library = library;
        library.getBooks().add(this);
    }
}
