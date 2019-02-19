package com.hellokoding.springboot.jpa.book;

import lombok.*;

import javax.persistence.*;

@Entity

@Data
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String title;

    private String description;
}
