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
    private Integer id;

    @Column(unique = true, nullable = false)
    private String code = UUID.randomUUID().toString();

    @OneToOne(mappedBy = "idCard", optional = false)
    private Person person;
}
