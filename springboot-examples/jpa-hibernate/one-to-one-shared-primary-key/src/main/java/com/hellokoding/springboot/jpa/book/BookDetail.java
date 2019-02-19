package com.hellokoding.springboot.jpa.book;

import lombok.Data;

import javax.persistence.*;

@Data

@Entity
public class BookDetail {
    @Id
    private Integer id;

    @OneToOne
    @JoinColumn
    @MapsId
    private Book book;

    private int numberOfPages;

    public BookDetail(int numberOfPages) {
        this.numberOfPages = numberOfPages;
    }
}
