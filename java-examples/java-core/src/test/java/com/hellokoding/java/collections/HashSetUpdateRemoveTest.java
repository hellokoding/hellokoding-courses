package com.hellokoding.java.collections;

import org.junit.Test;

import java.util.HashSet;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;

public class HashSetUpdateRemoveTest {
    @Test
    public void removeOneObject() {
        Set<Integer> set = new HashSet<>();
        set.add(3);
        set.add(1);
        set.add(2);

        boolean result = set.remove(1);
        assertThat(result).isTrue();

        assertThat(set).hasSize(2);
    }

    @Test
    public void removeNonExistingObject() {
        Set<Integer> set = new HashSet<>();
        set.add(3);
        set.add(1);
        set.add(2);

        boolean result = set.remove(4);
        assertThat(result).isFalse();

        assertThat(set).hasSize(3);
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
    public void removeAll() {
        Set<Integer> set = new HashSet<>();
        set.add(3);
        set.add(1);
        set.add(2);

        boolean result = set.removeAll(Set.of(4, 5));
        assertThat(result).isFalse();
        assertThat(set).hasSize(3);

        result = set.removeAll(Set.of(2, 4));
        assertThat(result).isTrue();
        assertThat(set).hasSize(2);
    }

    @Test
    public void retainAll() {
        Set<Integer> set = new HashSet<>();
        set.add(3);
        set.add(1);
        set.add(2);

        boolean result = set.retainAll(Set.of(1, 2, 3));
        assertThat(result).isFalse();
        assertThat(set).hasSize(3);

        result = set.retainAll(Set.of(2, 4, 5));
        assertThat(result).isTrue();
        assertThat(set).hasSize(1);
    }

    @Test
    public void clear() {
        Set<Integer> set = new HashSet<>();
        set.add(3);
        set.add(1);
        set.add(2);

        set.clear();
        assertThat(set).hasSize(0);
    }

    @Test
    public void updateAnObject() {
        Set<Integer> set = new HashSet<>();
        set.add(3);
        set.add(1);
        set.add(2);

        set.remove(2);
        set.add(4);

        assertThat(set).hasSize(3);
    }
}
