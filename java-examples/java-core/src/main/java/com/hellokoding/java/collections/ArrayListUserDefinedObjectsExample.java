package com.hellokoding.java.collections;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class ArrayListUserDefinedObjectsExample {
    public static void main(String[] args) {
        Book book1 = new Book(1, "Spring Boot In Practice");
        Book book2 = new Book(2, "Algorithms Bootcamp");
        List<Book> books = new ArrayList<>();
        books.add(book1);
        books.add(book2);

        System.out.println(books.contains(book1)); // true
        System.out.println(books.contains(book2)); // true
    }

    static class Book {
        private final int id;
        private final String title;

        public Book(int id, String title) {
            this.id = id;
            this.title = title;
        }

        public int getId() {
            return id;
        }

        public String getTitle() {
            return title;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Book book = (Book) o;
            return id == book.id &&
                Objects.equals(title, book.title);
        }

        @Override
        public int hashCode() {
            return Objects.hash(id, title);
        }
    }
}
