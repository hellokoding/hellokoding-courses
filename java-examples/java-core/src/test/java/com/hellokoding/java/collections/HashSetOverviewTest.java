package com.hellokoding.java.collections;

import org.junit.Test;

import java.util.HashSet;
import java.util.Set;
import java.util.TreeSet;

import static org.assertj.core.api.Assertions.assertThat;

public class HashSetOverviewTest {
    @Test
    public void declare() {
        Set<Integer> set1 = new HashSet<>();
        assertThat(set1).isInstanceOf(HashSet.class);

        HashSet<Integer> set2 = new HashSet<>();
    }

    @Test
    public void initializeWithAdd() {
        // Create a new HashSet
        Set<Integer> set = new HashSet<>();

        // Add elements to HashSet
        set.add(3);
        set.add(1);
        set.add(2);

        // Can add null element
        set.add(null);

        // Duplicate element will be ignored
        set.add(2);

        assertThat(set).hasSize(4);

        // The output ordering will be vary as Set is not reserved the insertion order
        System.out.println(set);
    }

    @Test
    public void iterate() {
        Set<Integer> set = new HashSet<>(Set.of(3, 1, 2));

        set.forEach(ele -> System.out.printf("%d ", ele));
    }

    @Test
    public void filter() {
        Set<Integer> set = new HashSet<>(Set.of(3, 1, 2));
        Integer[] arr = set.stream().filter(e -> e >= 2).toArray(Integer[]::new);

        assertThat(arr).contains(2, 3);
    }

    @Test
    public void removeIf() {
        Set<Integer> set = new HashSet<>();
        set.add(3);
        set.add(1);
        set.add(2);

        boolean result = set.removeIf(e -> e > 3);
        assertThat(result).isFalse();
        assertThat(set).hasSize(3);

        result = set.removeIf(e -> e > 1);
        assertThat(result).isTrue();
        assertThat(set).hasSize(1);
    }

    @Test
    public void sort() {
        Set<Integer> set = new HashSet<>(Set.of(3, 1, 2));
        Set<Integer> sortedSet = new TreeSet<>(set);

        // Asserts that the set contains the given values in order
        assertThat(sortedSet).containsExactly(1, 2, 3);
    }
}
