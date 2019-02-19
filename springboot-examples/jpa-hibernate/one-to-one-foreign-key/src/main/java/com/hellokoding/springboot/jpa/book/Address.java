package com.hellokoding.springboot.jpa.book;

import lombok.Data;

import javax.persistence.*;

@Data

@Entity
public class Address {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String street;

    private String zipCode;

    @OneToOne(mappedBy = "address")
    private Library library;

    public Address(String street, String zipCode) {
        this.street = street;
        this.zipCode = zipCode;
    }
}
