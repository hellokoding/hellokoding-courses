package com.hellokoding.jpa.book;

import javax.persistence.*;

@Entity(name = "Library")
@Table(name = "library")
public class Library {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "library_name")
    private String name;


}
