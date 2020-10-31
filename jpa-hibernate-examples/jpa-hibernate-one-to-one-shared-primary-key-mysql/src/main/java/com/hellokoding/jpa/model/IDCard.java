package com.hellokoding.jpa.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.UUID;

@Getter @Setter
@Entity
public class IDCard {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @OneToOne(optional = false)
    @JoinColumn(name = "person_id")
    @MapsId
    private Person person;

    @Column(unique = true, nullable = false)
    private String code = UUID.randomUUID().toString();
}
