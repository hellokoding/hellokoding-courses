package com.hellokoding.jpa.book;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import javax.persistence.*;
import java.util.Date;

@Entity

@Data
public class BookPublisher {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne
    @JoinColumn(name = "book_id")
    @JsonIgnoreProperties("bookPublishers")
    private Book book;

    @ManyToOne
    @JoinColumn(name = "publisher_id")
    @JsonIgnoreProperties("bookPublishers")
    private Publisher publisher;

    private Date publishedDate;
}
