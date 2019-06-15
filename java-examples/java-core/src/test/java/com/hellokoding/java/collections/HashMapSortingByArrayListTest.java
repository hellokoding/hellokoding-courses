package com.hellokoding.java.collections;

import org.junit.Test;

import java.util.*;
import java.util.stream.Collectors;

import static org.assertj.core.api.Assertions.assertThat;

public class HashMapSortingByArrayListTest {
    @Test
    public void givenNonComparableAndComparator_whenSortKeysAsc_thenSuccess(){
        // given
        Map<NonComparableBook, Integer> bookMap = new HashMap<>();
        bookMap.put(new NonComparableBook("C"), 5);
        bookMap.put(new NonComparableBook("A"), 2);
        bookMap.put(new NonComparableBook("D"), 8);

        // when sorting in ascending order
        List<NonComparableBook> bookList = new ArrayList<>(bookMap.keySet());
        Collections.sort(bookList, Comparator.comparing(e -> e.title));

        // then
        String actual = bookList.stream().map(e -> e.title).collect(Collectors.joining());
        assertThat(actual).isEqualTo("ACD");
    }

    @Test
    public void givenComparable_whenSortKeysAsc_thenSuccess(){
        // given
        Map<ComparableBook, Integer> bookMap = new HashMap<>();
        bookMap.put(new ComparableBook("C"), 5);
        bookMap.put(new ComparableBook("A"), 2);
        bookMap.put(new ComparableBook("D"), 8);

        // when sorting in natural / ascending order
        List<ComparableBook> bookList = new ArrayList<>(bookMap.keySet());
        Collections.sort(bookList);

        // then
        String actual = bookList.stream().map(e -> e.title).collect(Collectors.joining());
        assertThat(actual).isEqualTo("ACD");
    }

    @Test
    public void givenComparable_whenSortValuesAsc_thenSuccess(){
        // given
        Map<Integer, ComparableBook> bookMap = new HashMap<>();
        bookMap.put(5, new ComparableBook("C"));
        bookMap.put(2, new ComparableBook("A"));
        bookMap.put(8, new ComparableBook("D"));

        // when sorting in natural / ascending order
        List<ComparableBook> bookList = new ArrayList<>(bookMap.values());
        Collections.sort(bookList);

        // then
        String actual = bookList.stream().map(e -> e.title).collect(Collectors.joining());
        assertThat(actual).isEqualTo("ACD");
    }

    @Test
    public void givenComparable_whenSortValuesDesc_thenSuccess(){
        // given
        Map<Integer, ComparableBook> bookMap = new HashMap<>();
        bookMap.put(5, new ComparableBook("C"));
        bookMap.put(2, new ComparableBook("A"));
        bookMap.put(8, new ComparableBook("D"));

        // when sorting in reverse / descending order
        List<ComparableBook> bookList = new ArrayList<>(bookMap.values());
        Collections.sort(bookList, Comparator.reverseOrder());

        // then
        String actual = bookList.stream().map(e -> e.title).collect(Collectors.joining());
        assertThat(actual).isEqualTo("DCA");
    }
}
