package com.hellokoding.java.collections;

import org.assertj.core.api.ThrowableAssert;
import org.junit.Test;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class ArrayListSortingTest {
    @Test
    public void sortStrings(){
        List<String> strings = new ArrayList<>(List.of("c", "a", "b"));

        strings.sort(Comparator.naturalOrder());
        assertThat(strings).containsExactly("a", "b", "c");

        strings.sort(Comparator.reverseOrder());
        assertThat(strings).containsExactly("c", "b", "a");
    }

    @Test
    public void sortNumbers() {
        List<Integer> numbers = new ArrayList<>(List.of(3, 1, 2));

        numbers.sort(Comparator.naturalOrder());
        assertThat(numbers).containsExactly(1, 2, 3);

        numbers.sort(Comparator.reverseOrder());
        assertThat(numbers).containsExactly(3, 2, 1);
    }

    @Test
    public void sortDates() {
        LocalDate ld1 = LocalDate.of(2019, 3, 1);
        LocalDate ld2 = LocalDate.of(2019, 1, 1);
        LocalDate ld3 = LocalDate.of(2019, 2, 1);
        List<LocalDate> dates = new ArrayList<>(List.of(ld1, ld2, ld3));

        dates.sort(Comparator.naturalOrder());
        assertThat(dates).containsExactly(ld2, ld3, ld1);

        dates.sort(Comparator.reverseOrder());
        assertThat(dates).containsExactly(ld1, ld3, ld2);
    }

    @Test
    public void sortNullObject() {
        List<Integer> lst = new ArrayList<>(Arrays.asList(3, 1, null));

        ThrowableAssert.ThrowingCallable c = () -> lst.sort(Comparator.naturalOrder());
        assertThatThrownBy(c).isInstanceOf(NullPointerException.class);
    }

    @Test
    public void sortOneField() {
        Book book1 = new Book(1, "b");
        Book book2 = new Book(2, "c");
        Book book3 = new Book(3, "a");

        List<Book> list = Arrays.asList(book1, book2, book3);
        list.sort(Comparator.comparing(Book::getTitle));

        assertThat(list).containsExactly(book3, book1, book2);
    }

    @Test
    public void sortMultipleFields() {
        Book book1 = new Book(1, "b");
        Book book2 = new Book(2, "c");
        Book book3 = new Book(3, "c");

        List<Book> list = Arrays.asList(book1, book2, book3);
        list.sort(Comparator
            .comparing(Book::getTitle, Comparator.reverseOrder())
            .thenComparing(Book::getId, Comparator.reverseOrder()));

        assertThat(list).containsExactly(book3, book2, book1);
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
}
