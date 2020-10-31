package com.hellokoding.jpa.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;

@Data @AllArgsConstructor @NoArgsConstructor
@Embeddable
public class BookPublisherId implements Serializable {
    @Column(name = "book_id")
    private Integer bookId;

    @Column(name = "publisher_id")
    private Integer publisherId;
}
