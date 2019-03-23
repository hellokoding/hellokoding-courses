package com.hellokoding.jpa.book;

import lombok.*;

import javax.persistence.*;
import java.util.Objects;

@Data
@RequiredArgsConstructor
@NoArgsConstructor
@ToString(exclude = "category")

@Entity
public class Book{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private @NonNull String name;

    @ManyToOne
    @JoinColumn
    private Category category;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Book)) return false;
        Book book = (Book) o;
        return Objects.equals(name, book.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }
}
