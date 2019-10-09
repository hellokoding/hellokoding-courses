package com.hellokoding.java.collections;

import org.junit.Test;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;

public class HashSetInitializeTest {
    @Test
    public void initWithListOfAndSetOf() {
        Set<Integer> set1 = new HashSet<>(List.of(3, 1, 2));
        assertThat(set1).contains(3, 1, 2);

        Set<Integer> set2 = new HashSet<>(Set.of(5, 4, 6));
        assertThat(set2).contains(5, 4, 6);
    }

    @Test
    public void initWithArraysAsList() {
        Set<Integer> set = new HashSet<>(Arrays.asList(3, 1, 2, null));
        assertThat(set).contains(3, 1, 2, null);
    }

    @Test
    public void initFromAnExistingCollection() {
        Set<Integer> set1 = new HashSet<>(Arrays.asList(3, 1, 2, null));

        Set<Integer> set2 = new HashSet<>(set1);
        assertThat(set2).contains(3, 1, 2, null);
    }

    @Test
    public void initWithAddAndAddAll() {
        Set<Integer> set1 = new HashSet<>(Set.of(1, 2, 3));

        Set<Integer> set2 = new HashSet<>();
        set2.add(1);
        set2.add(1); // duplicated element will be ignored
        set2.add(null); // permits null element
        set2.addAll(set1);

        assertThat(set2).contains(1, 2, 3, null);
    }

    @Test
    public void initWithDoubleBrace() {
        Set<Integer> set = new HashSet<>(){{
            add(3);
            add(1);
            add(2);
        }};

        assertThat(set).contains(1, 2, 3);
    }
}
