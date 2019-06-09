package com.hellokoding.java.collections;

public class ComparableBook implements Comparable<ComparableBook> {
    String title;

    public ComparableBook(String title) {
        this.title = title;
    }

    @Override
    public int compareTo(ComparableBook o) {
        return this.title.compareTo(o.title);
    }
}
