package com.hellokoding.java.collections;

import org.junit.Test;

import java.util.*;
import java.util.stream.Collectors;

import static org.assertj.core.api.Assertions.assertThat;

public class HashMapSortingByTreeMapTest {
    @Test(expected = ClassCastException.class)
    public void givenNonComparable_whenSortKeys_thenThrowException(){
        // given
        Map<NonComparableBook, Integer> bookMap = new HashMap<>();
        bookMap.put(new NonComparableBook("C"), 5);
        bookMap.put(new NonComparableBook("A"), 2);
        bookMap.put(new NonComparableBook("D"), 8);

        // when sorting
        SortedMap<NonComparableBook, Integer> bookTreeMap = new TreeMap<>(bookMap);
    }

    @Test
    public void givenComparable_whenSortKeysAsc_thenSuccess(){
        // given
        Map<ComparableBook, Integer> bookMap = new HashMap<>();
        bookMap.put(new ComparableBook("C"), 5);
        bookMap.put(new ComparableBook("A"), 2);
        bookMap.put(new ComparableBook("D"), 8);

        // when sorting in natural / ascending order
        SortedMap<ComparableBook, Integer> sortedMap = new TreeMap<>(bookMap);

        // then
        String actual = sortedMap.keySet().stream().map(e -> e.title).collect(Collectors.joining());
        assertThat(actual).isEqualTo("ACD");
    }

    @Test
    public void givenComparable_whenSortKeysDesc_thenSuccess(){
        // given
        Map<ComparableBook, Integer> bookMap = new HashMap<>();
        bookMap.put(new ComparableBook("C"), 5);
        bookMap.put(new ComparableBook("A"), 2);
        bookMap.put(new ComparableBook("D"), 8);

        // when sorting in descending order
        SortedMap<ComparableBook, Integer> sortedMap = new TreeMap<>(bookMap).descendingMap();

        // then
        String actual = sortedMap.keySet().stream().map(e -> e.title).collect(Collectors.joining());
        assertThat(actual).isEqualTo("DCA");
    }
}
