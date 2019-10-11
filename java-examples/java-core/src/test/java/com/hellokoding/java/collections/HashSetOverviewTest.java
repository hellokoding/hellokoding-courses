package com.hellokoding.java.collections;

import org.junit.Test;

import java.util.*;

import static org.assertj.core.api.Assertions.assertThat;

public class HashSetOverviewTest {
    @Test
    public void declare() {
        Set<Integer> set1 = new HashSet<>();
        assertThat(set1).isInstanceOf(HashSet.class);

        HashSet<Integer> set2 = new HashSet<>();
    }

    @Test
    public void initWithListOfAndSetOf() {
        Set<Integer> set1 = new HashSet<>(List.of(3, 1, 2));
        assertThat(set1).contains(3, 1, 2);

        Set<Integer> set2 = new HashSet<>(Set.of(5, 4, 6));
        assertThat(set2).contains(5, 4, 6);
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

    @Test
    public void containsAddRemove() {
        Set<Integer> hashSet = new HashSet<>(List.of(1, 2, 3));
        Boolean contained = hashSet.contains(3);
        assertThat(contained).isTrue();

        hashSet.add(1); // duplicated element is ignored
        hashSet.add(null); // permits null element
        assertThat(hashSet).contains(1, 2, 3, null);

        hashSet.remove(2);
        assertThat(hashSet).contains(1, 3, null);
    }

    @Test
    public void containsAddRemoveACustomObject(){
        Set<Book> hashSet = new HashSet<>();
        hashSet.add(new Book(1, "a"));

        Boolean contained = hashSet.contains(new Book(1, "a"));
        assertThat(contained).isFalse();

        hashSet.add(new Book(1, "a"));
        assertThat(hashSet).hasSize(2);

        hashSet.remove(new Book(1, "a"));
        assertThat(hashSet).hasSize(2);
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
