package com.hellokoding.java.collections;

import org.junit.Test;

import java.util.*;

import static org.assertj.core.api.Assertions.assertThat;

public class HashSetSortingTest {
    @Test
    public void sortAscWithTreeSet() {
        Set<Integer> set = new HashSet<>(Set.of(3, 1, 2));
        NavigableSet<Integer> sortedSet = new TreeSet<>(set);

        // Asserts that the set contains the given values in order
        assertThat(sortedSet).containsExactly(1, 2, 3);
    }

    @Test
    public void sortDescWithTreeSet() {
        Set<Integer> set = new HashSet<>(Set.of(3, 1, 2));
        NavigableSet<Integer> sortedSet = new TreeSet<>(set);
        NavigableSet<Integer> descSet = sortedSet.descendingSet();

        assertThat(descSet).containsExactly(3, 2, 1);
    }

    @Test
    public void sortWithTreeSetAndComparator() {
        Set<Integer> set = new HashSet<>(Set.of(3, 1, 2));
        NavigableSet<Integer> sortedSet = new TreeSet<>(Comparator.reverseOrder());
        sortedSet.addAll(set);

        assertThat(sortedSet).containsExactly(3, 2, 1);
    }

    @Test
    public void sortAscWithArrayList() {
        Set<String> set = new HashSet<>(Set.of("c", "a", "b"));
        List<String> list = new ArrayList<>(set);
        list.sort(Comparator.naturalOrder());

        assertThat(list).containsExactly("a", "b", "c");
    }

    @Test
    public void sortDescWithArrayList() {
        Set<String> set = new HashSet<>(Set.of("c", "a", "b"));
        List<String> list = new ArrayList<>(set);
        list.sort(Comparator.reverseOrder());

        assertThat(list).containsExactly("c", "b", "a");
    }
}
