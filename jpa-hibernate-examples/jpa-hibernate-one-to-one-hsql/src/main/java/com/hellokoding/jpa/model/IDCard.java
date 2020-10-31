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
    private int personId;

    @OneToOne(cascade = CascadeType.ALL, optional = false)
    @PrimaryKeyJoinColumn(name = "person_id", referencedColumnName = "id")
    private Person person;

    @Column(unique = true, nullable = false)
    private String code = UUID.randomUUID().toString();

    public IDCard(Person person) {
        this.person = person;
    }
}
