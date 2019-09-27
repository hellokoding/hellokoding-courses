package com.hellokoding.java.collections;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class ArrayListQueryTest {
    @Test
    public void filter() {
        List<Integer> lst = new ArrayList<>();
        lst.add(3);
        lst.add(1);
        lst.add(2);

        Integer[] arr = lst.stream().filter(e -> e >= 2).toArray(Integer[]::new);
        assertThat(arr).contains(2, 3);
    }

    @Test
    public void get_thenOK() {
        List<Integer> lst = new ArrayList<>();
        lst.add(3);
        lst.add(1);
        lst.add(2);

        int ele = lst.get(0);
        assertThat(ele).isEqualTo(3);
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void get_thenException() {
        List<Integer> lst = new ArrayList<>();
        lst.add(3);
        lst.add(1);
        lst.add(2);

        // throws IndexOutOfBoundsException
        int ele = lst.get(4);
    }

    @Test
    public void indexOf() {
        List<Integer> lst = new ArrayList<>();
        lst.add(3);
        lst.add(1);
        lst.add(2);

        int idx = lst.indexOf(3);
        assertThat(idx).isEqualTo(0);

        idx = lst.indexOf(4);
        assertThat(idx).isEqualTo(-1);
    }

    @Test
    public void lastIndexOf() {
        List<Integer> lst = new ArrayList<>();
        lst.add(3);
        lst.add(1);
        lst.add(3);

        int idx = lst.lastIndexOf(3);
        assertThat(idx).isEqualTo(2);

        idx = lst.indexOf(4);
        assertThat(idx).isEqualTo(-1);
    }

    @Test
    public void contains() {
        List<Integer> lst = new ArrayList<>();
        lst.add(3);
        lst.add(1);
        lst.add(2);

        boolean contained = lst.contains(1);
        assertThat(contained).isTrue();
    }

    @Test
    public void containsAll() {
        List<Integer> lst = new ArrayList<>();
        lst.add(3);
        lst.add(1);
        lst.add(2);

        boolean contained = lst.containsAll(List.of(1, 3));
        assertThat(contained).isTrue();

        contained = lst.containsAll(List.of(1, 4));
        assertThat(contained).isFalse();
    }

    @Test
    public void size() {
        List<Integer> lst = new ArrayList<>();
        lst.add(3);
        lst.add(1);
        lst.add(2);

        int size = lst.size();
        assertThat(size).isEqualTo(3);
    }

    @Test
    public void isEmpty() {
        List<Integer> lst = new ArrayList<>();
        lst.add(3);
        lst.add(1);
        lst.add(2);

        boolean empty = lst.isEmpty();
        assertThat(empty).isFalse();
    }
}
