package com.hellokoding.java.collections;

import org.junit.Test;

import java.util.*;
import java.util.stream.Collectors;

import static org.assertj.core.api.Assertions.assertThat;

public class HashSetSortingByArrayListTest {
    @Test
    public void givenNonComparableAndComparator_whenSortAsc_thenSuccess(){
        // given
        HashSet<NonComparableBook> bookSet = new HashSet<>();
        bookSet.add(new NonComparableBook("C"));
        bookSet.add(new NonComparableBook("A"));
        bookSet.add(new NonComparableBook("D"));

        // when sorting in natural / ascending order
        List<NonComparableBook> bookList = new ArrayList<>(bookSet);
        Collections.sort(bookList, Comparator.comparing(e -> e.title));

        // then
        String actual = bookList.stream().map(e -> e.title).collect(Collectors.joining());
        assertThat(actual).isEqualTo("ACD");
    }

    @Test
    public void givenComparable_whenSortAsc_thenSuccess(){
        // given
        HashSet<ComparableBook> bookSet = new HashSet<>();
        bookSet.add(new ComparableBook("C"));
        bookSet.add(new ComparableBook("A"));
        bookSet.add(new ComparableBook("D"));

        // when sorting in natural / ascending order
        List<ComparableBook> bookList = new ArrayList<>(bookSet);
        Collections.sort(bookList);

        // then
        String actual = bookList.stream().map(e -> e.title).collect(Collectors.joining());
        assertThat(actual).isEqualTo("ACD");
    }

    @Test
    public void givenComparable_whenSortDesc_thenSuccess(){
        // given
        HashSet<ComparableBook> bookSet = new HashSet<>();
        bookSet.add(new ComparableBook("C"));
        bookSet.add(new ComparableBook("A"));
        bookSet.add(new ComparableBook("D"));

        // when sorting in reverse / descending order
        List<ComparableBook> bookList = new ArrayList<>(bookSet);
        Collections.sort(bookList, Comparator.reverseOrder());

        // then
        String actual = bookList.stream().map(e -> e.title).collect(Collectors.joining());
        assertThat(actual).isEqualTo("DCA");
    }
}
