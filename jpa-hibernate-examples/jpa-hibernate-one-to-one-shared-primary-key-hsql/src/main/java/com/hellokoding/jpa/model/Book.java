package com.hellokoding.jpa.model;

import javax.persistence.*;
import java.io.Serializable;

@Entity
public class Book implements Serializable{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String name;

    @OneToOne(cascade = CascadeType.ALL, mappedBy = "book")
    private BookDetail bookDetail;

    public Book(){

    }

    public Book(String name){
        this.name = name;
    }

    public Book(String name, BookDetail bookDetail){
        this.name = name;
        this.bookDetail = bookDetail;
        this.bookDetail.setBook(this);
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

    public BookDetail getBookDetail() {
        return bookDetail;
    }

    public void setBookDetail(BookDetail bookDetail) {
        this.bookDetail = bookDetail;
    }

    @Override
    public String toString() {
        return String.format(
                "Book[id=%d, name='%s', number of pages='%d']",
                id, name, bookDetail.getNumberOfPages());
    }
}
