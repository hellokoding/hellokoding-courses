package com.hellokoding.java.lang;


import java.util.Objects;

public class BookWithEquals {
    public String title;
    public Integer pages;

    public BookWithEquals(String title, Integer pages) {
        this.title = title;
        this.pages = pages;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BookWithEquals that = (BookWithEquals) o;
        return Objects.equals(title, that.title) &&
                Objects.equals(pages, that.pages);
    }
}
