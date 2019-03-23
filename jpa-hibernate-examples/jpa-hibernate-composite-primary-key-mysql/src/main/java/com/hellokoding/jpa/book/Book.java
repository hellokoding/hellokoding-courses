package com.hellokoding.jpa.book;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Data
@NoArgsConstructor

@Entity
@IdClass(Book.IdClass.class)
public class Book {
    @Id
    private String name;

    @Id
    private Date publishedDate;

    private Date updatedDate = new Date();

    public Book(String name, Date publishedDate) {
        this.name = name;
        this.publishedDate = publishedDate;
    }

    @Data
    static class IdClass implements Serializable {
        private String name;
        private Date publishedDate;
    }
}
