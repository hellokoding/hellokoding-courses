package com.hellokoding.java.collections;

import org.junit.Test;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;

public class HashSetInitializeTest {
    @Test
    public void initializeInMultipleLines() {
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
    public void initializeInOneLine() {
        // Create a new HashSet
        Set<Integer> set = new HashSet<>(){{
            add(3);
            add(1);
            add(2);
        }};

        assertThat(set).hasSize(3);

        // The output ordering will be vary as Set is not reserved the insertion order
        System.out.println(set);
    }

    @Test
    public void initializeByAddAll() {
        // Create a new HashSet
        Set<Integer> set = new HashSet<>();

        // Add all from List
        set.addAll(List.of(3, 1, 2));

        // Add all from Set
        set.addAll(Set.of(5, 4, 6));

        assertThat(set).hasSize(6);

        // The output ordering will be vary as Set is not reserved the insertion order
        System.out.println(set);
    }

    @Test
    public void initializeFromAnotherCollectionWhenCreating() {
        // create and initialize a HashSet from Java 9+ List.of
        Set<Integer> set1 = new HashSet<>(List.of(3, 1, 2));
        assertThat(set1).hasSize(3);

        // create and initialize a HashSet from Arrays.asList
        Set<Integer> set2 = new HashSet<>(Arrays.asList(3, 1, 2));
        assertThat(set2).hasSize(3);

        // create and initialize a HashSet from Java 9+ Set.of
        Set<Integer> set3 = new HashSet<>(Set.of(5, 4, 6));
        assertThat(set3).hasSize(3);
    }
}
