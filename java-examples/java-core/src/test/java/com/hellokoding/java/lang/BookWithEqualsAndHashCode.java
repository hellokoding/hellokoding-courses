package com.hellokoding.java.lang;


import java.util.Objects;

public class BookWithEqualsAndHashCode {
    public String title;
    public Integer pages;

    public BookWithEqualsAndHashCode(String title, Integer pages) {
        this.title = title;
        this.pages = pages;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BookWithEqualsAndHashCode that = (BookWithEqualsAndHashCode) o;
        return Objects.equals(title, that.title) &&
                Objects.equals(pages, that.pages);
    }

    @Override
    public int hashCode() {
        return Objects.hash(title, pages);
    }
}
