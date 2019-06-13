package com.hellokoding.java.collections;

import org.junit.Test;

import java.util.*;
import java.util.stream.Collectors;

import static org.assertj.core.api.Assertions.assertThat;

public class ArrayListSortingTest {
    @Test
    public void givenNonComparableAndComparator_whenSortAsc_thenSuccess(){
        // given
        List<NonComparableBook> bookList = new ArrayList<>();
        bookList.add(new NonComparableBook("C"));
        bookList.add(new NonComparableBook("A"));
        bookList.add(new NonComparableBook("D"));

        // when sorting in natural / ascending order
        Collections.sort(bookList, Comparator.comparing(e -> e.title));

        // then
        String actual = bookList.stream().map(e -> e.title).collect(Collectors.joining());
        assertThat(actual).isEqualTo("ACD");
    }

    @Test
    public void givenComparable_whenSortAsc_thenSuccess(){
        // given
        List<ComparableBook> bookList = new ArrayList<>();
        bookList.add(new ComparableBook("C"));
        bookList.add(new ComparableBook("A"));
        bookList.add(new ComparableBook("D"));

        // when sorting in natural / ascending order
        Collections.sort(bookList);

        // then
        String actual = bookList.stream().map(e -> e.title).collect(Collectors.joining());
        assertThat(actual).isEqualTo("ACD");
    }

    @Test
    public void givenComparable_whenSortDesc_thenSuccess(){
        // given
        List<ComparableBook> bookList = new ArrayList<>();
        bookList.add(new ComparableBook("C"));
        bookList.add(new ComparableBook("A"));
        bookList.add(new ComparableBook("D"));

        // when sorting in reverse / descending order
        Collections.sort(bookList, Comparator.reverseOrder());

        // then
        String actual = bookList.stream().map(e -> e.title).collect(Collectors.joining());
        assertThat(actual).isEqualTo("DCA");
    }
}
