package com.hellokoding.java.collections;

import org.junit.Test;

import java.util.*;
import java.util.stream.Collectors;

import static org.assertj.core.api.Assertions.assertThat;

public class ArraySortingTest {
    @Test(expected = ClassCastException.class)
    public void givenNonComparableAndComparator_whenSort_thenThrowsException(){
        // given
        NonComparableBook[] books = new NonComparableBook[3];
        books[0] = new NonComparableBook("C");
        books[1] = new NonComparableBook("A");
        books[2] = new NonComparableBook("D");

        // when sorting in natural / ascending order
        Arrays.sort(books);
    }

    @Test
    public void givenComparable_whenSortAsc_thenSuccess(){
        // given
        ComparableBook[] books = new ComparableBook[3];
        books[0] = new ComparableBook("C");
        books[1] = new ComparableBook("A");
        books[2] = new ComparableBook("D");

        // when sorting in natural / ascending order
        Arrays.sort(books);

        // then
        String actual = Arrays.stream(books).map(e -> e.title).collect(Collectors.joining());
        assertThat(actual).isEqualTo("ACD");
    }

    @Test
    public void givenComparable_whenSortDesc_thenSuccess(){
        // given
        ComparableBook[] books = new ComparableBook[3];
        books[0] = new ComparableBook("C");
        books[1] = new ComparableBook("A");
        books[2] = new ComparableBook("D");

        // when sorting in reverse / descending order
        Arrays.sort(books, Comparator.reverseOrder());

        // then
        String actual = Arrays.stream(books).map(e -> e.title).collect(Collectors.joining());
        assertThat(actual).isEqualTo("DCA");
    }
}
