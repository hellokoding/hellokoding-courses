package com.hellokoding.jpa.model;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "book_detail")
public class BookDetail implements Serializable{
    @Column(name = "number_of_pages")
    private Integer numberOfPages;

    @Id
    @OneToOne
    @JoinColumn(name = "book_id")
    private Book book;

    public BookDetail(){

    }

    public BookDetail(Integer numberOfPages){
        this.numberOfPages = numberOfPages;
    }

    public Integer getNumberOfPages() {
        return numberOfPages;
    }

    public void setNumberOfPages(Integer numberOfPages) {
        this.numberOfPages = numberOfPages;
    }

    public Book getBook() {
        return book;
    }

    public void setBook(Book book) {
        this.book = book;
    }
}
