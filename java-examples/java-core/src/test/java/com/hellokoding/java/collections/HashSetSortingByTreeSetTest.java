package com.hellokoding.java.collections;

import org.junit.Test;

import java.util.*;
import java.util.stream.Collectors;

import static org.assertj.core.api.Assertions.assertThat;

public class HashSetSortingByTreeSetTest {
    @Test(expected = ClassCastException.class)
    public void givenNonComparable_whenSortAsc_thenThrowException(){
        // given
        HashSet<NonComparableBook> bookSet = new HashSet<>();
        bookSet.add(new NonComparableBook("C"));
        bookSet.add(new NonComparableBook("A"));
        bookSet.add(new NonComparableBook("D"));

        // when sorting in the natural / ascending order
        SortedSet<NonComparableBook> bookTreeSet = new TreeSet<>(bookSet);
    }

    @Test
    public void givenComparable_whenSortAsc_thenSuccess(){
        // given
        HashSet<ComparableBook> bookSet = new HashSet<>();
        bookSet.add(new ComparableBook("C"));
        bookSet.add(new ComparableBook("A"));
        bookSet.add(new ComparableBook("D"));

        // when sorting in the natural / ascending order
        SortedSet<ComparableBook> sortedSet = new TreeSet<>(bookSet);

        // then
        String actual = sortedSet.stream().map(k -> k.title).collect(Collectors.joining());
        assertThat(actual).isEqualTo("ACD");
    }

    @Test
    public void givenComparable_whenSortDesc_thenSuccess(){
        // given
        HashSet<ComparableBook> bookSet = new HashSet<>();
        bookSet.add(new ComparableBook("C"));
        bookSet.add(new ComparableBook("A"));
        bookSet.add(new ComparableBook("D"));

        // when sorting in the reverse / descending order
        SortedSet<ComparableBook> sortedSet = new TreeSet<>(bookSet).descendingSet();

        // then
        String actual = sortedSet.stream().map(k -> k.title).collect(Collectors.joining());
        assertThat(actual).isEqualTo("DCA");
    }
}
