package com.hellokoding.jpa.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;

@Getter @Setter @NoArgsConstructor
@Entity
@Table(name = "book_publisher")
public class BookPublisher {
    @EmbeddedId
    private BookPublisherId id;

    @ManyToOne
    @MapsId("bookId")
    @JoinColumn(name = "book_id")
    private Book book;

    @ManyToOne
    @MapsId("publisherId")
    @JoinColumn(name = "publisher_id")
    private Publisher publisher;

    @Column(name = "published_date")
    private Date publishedDate;

    public BookPublisher(Book book, Publisher publisher, Date publishedDate) {
        this.id = new BookPublisherId(book.getId(), publisher.getId());
        this.book = book;
        this.publisher = publisher;
        this.publishedDate = publishedDate;
    }
}
