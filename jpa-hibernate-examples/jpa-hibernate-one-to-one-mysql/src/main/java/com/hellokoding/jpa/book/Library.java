package com.hellokoding.jpa.book;

import lombok.Data;

import javax.persistence.*;

@Data

@Entity
public class Library {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String name;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(unique = true)
    private Address address;

    public Library(String name, Address address) {
        this.name = name;
        this.address = address;
        this.address.setLibrary(this);
    }
}
