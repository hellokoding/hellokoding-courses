package com.hellokoding.java.collections;

import org.junit.Test;

import java.util.*;
import java.util.stream.Collectors;

import static org.assertj.core.api.Assertions.assertThat;

public class HashMapSortingByTreeSetTest {
    @Test(expected = ClassCastException.class)
    public void givenNonComparable_whenSortKeys_thenThrowException(){
        // given
        Map<NonComparableBook, Integer> bookMap = new HashMap<>();
        bookMap.put(new NonComparableBook("C"), 5);
        bookMap.put(new NonComparableBook("A"), 2);
        bookMap.put(new NonComparableBook("D"), 8);

        // when sorting
        TreeSet<NonComparableBook> bookTreeSet = new TreeSet<>(bookMap.keySet());
    }

    @Test
    public void givenComparable_whenSortKeysAsc_thenSuccess(){
        // given
        Map<ComparableBook, Integer> bookMap = new HashMap<>();
        bookMap.put(new ComparableBook("C"), 5);
        bookMap.put(new ComparableBook("A"), 2);
        bookMap.put(new ComparableBook("D"), 8);

        // when sorting in natural / ascending order
        SortedSet<ComparableBook> sortedSet = new TreeSet<>(bookMap.keySet());

        // then
        String actual = sortedSet.stream().map(k -> k.title).collect(Collectors.joining());
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
        SortedSet<ComparableBook> sortedSet = new TreeSet<>(bookMap.values());

        // then
        String actual = sortedSet.stream().map(v -> v.title).collect(Collectors.joining());
        assertThat(actual).isEqualTo("ACD");
    }

    @Test
    public void givenComparable_whenSortValuesDesc_thenSuccess(){
        // given
        Map<Integer, ComparableBook> bookMap = new HashMap<>();
        bookMap.put(5, new ComparableBook("C"));
        bookMap.put(2, new ComparableBook("A"));
        bookMap.put(8, new ComparableBook("D"));

        // when sorting in descending order
        SortedSet<ComparableBook> sortedSet = new TreeSet<>(bookMap.values()).descendingSet();

        // then
        String actual = sortedSet.stream().map(v -> v.title).collect(Collectors.joining());
        assertThat(actual).isEqualTo("DCA");
    }
}
