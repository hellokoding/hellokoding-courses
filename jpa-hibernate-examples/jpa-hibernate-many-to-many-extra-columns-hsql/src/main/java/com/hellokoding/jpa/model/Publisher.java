package com.hellokoding.jpa.model;

import lombok.Data;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Data
@Entity
public class Publisher {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String name;

    @OneToMany(mappedBy = "publisher")
    private Set<BookPublisher> bookPublishers = new HashSet<>();

    public Publisher(String name){
        this.name = name;
    }
}
