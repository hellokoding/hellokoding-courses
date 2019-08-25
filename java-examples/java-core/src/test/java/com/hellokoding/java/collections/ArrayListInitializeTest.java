package com.hellokoding.java.collections;

import org.junit.Test;

import java.util.*;

import static org.assertj.core.api.Assertions.assertThat;

public class ArrayListInitializeTest {
    @Test
    public void initializeInMultipleLines() {
        // Create a new ArrayList
        List<Integer> lst = new ArrayList<>();

        // Add elements to ArrayList
        lst.add(3);
        lst.add(1);
        lst.add(2);

        // Can add null element
        lst.add(null);

        // Can add duplicate element
        lst.add(2);
        assertThat(lst).hasSize(5);

        // The output ordering will be same as the insertion oder
        System.out.println(lst);
    }

    @Test
    public void initializeInOneLine() {
        // Create a new ArrayList
        List<Integer> lst = new ArrayList<>(){{
            add(3);
            add(1);
            add(2);
        }};

        assertThat(lst).hasSize(3);

        // The output ordering will be same as the insertion oder
        System.out.println(lst);
    }

    @Test
    public void initializeByAddAll() {
        // Create a new ArrayList
        List<Integer> lst1 = new ArrayList<>();

        // Add all from Java 9+ List.of
        lst1.addAll(List.of(3, 1, 2));

        // Add all from Arrays.asList
        lst1.addAll(Arrays.asList(5, 4, 6));

        // Add all from Java 9+ Set.of
        lst1.addAll(Set.of(8, 7, 9));

        assertThat(lst1).hasSize(9);

        // Add all from an existing collection
        List<Integer> lst2 = new ArrayList<>();
        lst2.addAll(lst1);
        assertThat(lst2).hasSize(9);
    }

    @Test
    public void initializeFromAnotherCollectionWhenCreating() {
        // create and initialize an ArrayList from Java 9+ List.of
        List<Integer> lst1 = new ArrayList<>(List.of(3, 1, 2));
        assertThat(lst1).hasSize(3);

        // create and initialize an ArrayList from Arrays.asList
        List<Integer> lst2 = new ArrayList<>(Arrays.asList(3, 1, 2));
        assertThat(lst2).hasSize(3);

        // create and initialize an ArrayList from Java 9+ Set.of
        List<Integer> lst3 = new ArrayList<>(Set.of(5, 4, 6));
        assertThat(lst3).hasSize(3);

        // create and initialize from an existing collection
        List<Integer> lst4 = new ArrayList<>(lst3);
        assertThat(lst4).hasSize(3);
    }
}
