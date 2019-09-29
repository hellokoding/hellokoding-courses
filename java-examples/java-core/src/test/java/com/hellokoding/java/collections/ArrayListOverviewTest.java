package com.hellokoding.java.collections;

import org.junit.Test;

import java.util.*;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class ArrayListOverviewTest {
    @Test
    public void declare() {
        List<Integer> lst1 = new ArrayList<>();
        assertThat(lst1).isInstanceOf(ArrayList.class);

        Collection<Integer> lst2 = new ArrayList<>();
        assertThat(lst2).isInstanceOf(ArrayList.class);

        Iterable<Integer> lst3 = new ArrayList<>();
        assertThat(lst3).isInstanceOf(ArrayList.class);

        ArrayList<Integer> lst4 = new ArrayList<>();
    }

    @Test
    public void initialize() {
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
    public void forEachConsumer() {
        List<Integer> lst = new ArrayList<>();
        lst.add(1);
        lst.add(2);
        lst.add(3);

        lst.forEach(e -> System.out.printf("%d ", e));
    }

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
    public void insert() {
        List<Integer> lst = new ArrayList<>();
        lst.add(1);
        lst.add(2);

        // Inserts element to lst at the specified index
        lst.add(0, 3);

        // Asserts that the list contains exactly the given values in order
        assertThat(lst).containsExactly(3, 1, 2);

        // Asserts that the list throws IndexOutOfBoundsException
        //     when insert element at the out of bound position
        assertThatThrownBy(() -> lst.add(5, 4))
                .isInstanceOf(IndexOutOfBoundsException.class);
    }
}
