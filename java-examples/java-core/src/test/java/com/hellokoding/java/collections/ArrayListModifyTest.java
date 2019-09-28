package com.hellokoding.java.collections;


import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class ArrayListModifyTest {
    @Test
    public void append() {
        List<Integer> lst = new ArrayList<>(Arrays.asList(3, 1, 2));

        // Appends elements to lst
        lst.add(4);

        // Can append null element
        lst.add(null);

        // Can append duplicate element
        lst.add(2);

        // Asserts that the list contains exactly the given values in order
        assertThat(lst).containsExactly(3, 1, 2, 4, null, 2);
    }

    @Test
    public void appendAll() {
        List<Integer> lst = new ArrayList<>();
        lst.add(3);

        // Appends all elements to lst
        lst.addAll(Arrays.asList(1, 2));

        assertThat(lst).containsExactly(3, 1, 2);
    }

    @Test
    public void insert() {
        List<Integer> lst = new ArrayList<>();
        lst.add(1);
        lst.add(2);

        // Inserts element to lst at the specified index
        lst.add(0, 3);

        assertThat(lst).containsExactly(3, 1, 2);

        // Asserts that the list throws IndexOutOfBoundsException
        //     when insert element at the out of bound position
        assertThatThrownBy(() -> lst.add(5, 4))
            .isInstanceOf(IndexOutOfBoundsException.class);
    }

    @Test
    public void insertAll() {
        List<Integer> lst = new ArrayList<>();
        lst.add(2);

        // Inserts a collection of elements to lst at the specified index
        lst.addAll(0, Arrays.asList(3, 1));

        assertThat(lst).containsExactly(3, 1, 2);

        assertThatThrownBy(() -> lst.addAll(4, Arrays.asList(4, 5)))
                .isInstanceOf(IndexOutOfBoundsException.class);
    }

    @Test
    public void set() {
        List<Integer> lst = new ArrayList<>();
        lst.add(1);
        lst.add(1);
        lst.add(2);

        // Updates element at the specified index
        lst.set(0, 3);

        assertThat(lst).containsExactly(3, 1, 2);
    }

    @Test
    public void removeElement() {
        List<Integer> lst = new ArrayList<>();
        lst.add(3);
        lst.add(1);
        lst.add(3);

        // Removes the first occurrence of the specified element from lst
        lst.remove(Integer.valueOf(3));

        assertThat(lst).containsExactly(1, 3);
    }

    @Test
    public void removeAtIndex() {
        List<Integer> lst = new ArrayList<>();
        lst.add(3);
        lst.add(1);
        lst.add(3);

        // Removes element at the specified position in lst
        lst.remove(0);

        assertThat(lst).containsExactly(1, 3);

        assertThatThrownBy(() -> lst.remove(2))
            .isInstanceOf(IndexOutOfBoundsException.class);
    }

    @Test
    public void removeAll() {
        List<Integer> lst = new ArrayList<>();
        lst.add(3);
        lst.add(1);
        lst.add(3);

        // Removes elements in lst that are contained in the specified collection
        lst.removeAll(List.of(4, 1, 3));

        assertThat(lst).isEmpty();
    }

    @Test
    public void retainAll() {
        List<Integer> lst = new ArrayList<>();
        lst.add(3);
        lst.add(1);
        lst.add(3);

        // Retains elements in lst that are contained in the specified collection
        lst.retainAll(List.of(2, 3));

        assertThat(lst).containsExactly(3, 3);
    }

    @Test
    public void clear() {
        List<Integer> lst = new ArrayList<>();
        lst.add(3);
        lst.add(1);
        lst.add(3);

        // Removes all of the elements from lst
        lst.clear();

        assertThat(lst).isEmpty();
    }
}
