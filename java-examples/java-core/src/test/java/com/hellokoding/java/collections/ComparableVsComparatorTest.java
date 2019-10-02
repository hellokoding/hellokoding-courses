package com.hellokoding.java.collections;

import org.junit.Test;

import java.util.*;

import static org.assertj.core.api.Assertions.assertThat;

public class ComparableVsComparatorTest {
    @Test
    public void provideDefaultSortKeyWithComparable() {
        ComparableBook book1 = new ComparableBook(1, "b");
        ComparableBook book2 = new ComparableBook(2, "c");
        ComparableBook book3 = new ComparableBook(3, "a");

        List<ComparableBook> list = Arrays.asList(book1, book2, book3);
        list.sort(Comparator.naturalOrder());
        assertThat(list).containsExactly(book3, book1, book2);
    }

    @Test
    public void provideSortKeyWithComparator() {
        Book book1 = new Book(1, "b");
        Book book2 = new Book(2, "c");
        Book book3 = new Book(3, "a");

        List<Book> list = Arrays.asList(book1, book2, book3);
        list.sort(Comparator.comparing(Book::getTitle));
        assertThat(list).containsExactly(book3, book1, book2);
    }

    @Test
    public void reverseTheDefaultOrderingWithComparator() {
        ComparableBook book1 = new ComparableBook(1, "b");
        ComparableBook book2 = new ComparableBook(2, "c");
        ComparableBook book3 = new ComparableBook(3, "a");

        List<ComparableBook> list = Arrays.asList(book1, book2, book3);
        list.sort(Comparator.reverseOrder());
        assertThat(list).containsExactly(book2, book1, book3);
    }

    @Test
    public void overrideSortKeyWithComparator() {
        ComparableBook book1 = new ComparableBook(1, "b");
        ComparableBook book2 = new ComparableBook(2, "c");
        ComparableBook book3 = new ComparableBook(3, "a");

        List<ComparableBook> list = Arrays.asList(book2, book3, book1);
        list.sort(Comparator.comparing(ComparableBook::getId));
        assertThat(list).containsExactly(book1, book2, book3);
    }

    @Test
    public void compareMultipleFieldsWithComparator() {
        Book book1 = new Book(1, "b");
        Book book2 = new Book(2, "c");
        Book book3 = new Book(3, "a");

        List<Book> list = Arrays.asList(book1, book2, book3);
        list.sort(Comparator
            .comparing(Book::getTitle)
            .thenComparing(Book::getId));
        assertThat(list).containsExactly(book3, book1, book2);
    }

    @Test
    public void reverseTheComparingOfComparator() {
        Book book1 = new Book(1, "b");
        Book book2 = new Book(2, "c");
        Book book3 = new Book(3, "c");

        List<Book> list = Arrays.asList(book1, book2, book3);
        list.sort(Comparator
                .comparing(Book::getTitle, Comparator.reverseOrder())
                .thenComparing(Book::getId, Comparator.reverseOrder()));

        assertThat(list).containsExactly(book3, book2, book1);
    }

    @Test
    public void reverseTheComparingOfComparator2() {
        Book book1 = new Book(1, "b");
        Book book2 = new Book(2, "c");
        Book book3 = new Book(3, "a");

        List<Book> list = Arrays.asList(book1, book2, book3);
        list.sort(Comparator
                .comparing(Book::getTitle)
                .reversed());

        assertThat(list).containsExactly(book2, book1, book3);
    }

    static class Book {
        int id;
        String title;

        Book(int id, String title) {
            this.id = id;
            this.title = title;
        }

        int getId() {
            return id;
        }

        String getTitle() {
            return title;
        }
    }

    static class ComparableBook implements Comparable<ComparableBook> {
        int id;
        String title;

        ComparableBook(int id, String title) {
            this.id = id;
            this.title = title;
        }

        int getId() {
            return id;
        }

        String getTitle() {
            return title;
        }

        @Override
        public int compareTo(ComparableBook o) {
            //return this.title.compareTo(o.title);
            return Comparator
                .comparing(ComparableBook::getTitle)
                .thenComparing(ComparableBook::getId)
                .compare(this, o);
        }
    }
}
