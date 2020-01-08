package com.hellokoding.java.collections;

import org.junit.Test;

import java.util.*;

import static org.assertj.core.api.Assertions.assertThat;

public class ArrayListInitializeTest {
    @Test
    public void initWithListOfAndSetOf() {
        List<Integer> arrayList1 = new ArrayList<>(List.of(3, 1, 2));
        assertThat(arrayList1).contains(3, 1, 2);

        List<Integer> arrayList2 = new ArrayList<>(Set.of(5, 4, 6));
        assertThat(arrayList2).contains(5, 4, 6);
    }

    @Test
    public void initWithArraysAsList() {
        List<Integer> arrayList1 = new ArrayList<>(Arrays.asList(3, 1, 2, null));
        assertThat(arrayList1).contains(3, 1, 2, null);
    }

    @Test
    public void initFromAnExistingCollection() {
        List<Integer> arrayList1 = new ArrayList<>(Arrays.asList(3, 1, 2, null));

        List<Integer> arrayList2 = new ArrayList<>(arrayList1);
        assertThat(arrayList2).contains(3, 1, 2, null);
    }

    @Test
    public void initWithAddAndAddAll() {
        List<Integer> arrayList1 = new ArrayList<>(Set.of(1, 2, 3));

        List<Integer> arrayList2 = new ArrayList<>();
        arrayList1.add(1);
        arrayList1.add(1); // duplicated element is ignored
        arrayList1.add(null); // permits null element
        arrayList2.addAll(arrayList1);

        assertThat(arrayList2).contains(1, 2, 3, null);
    }

    @Test
    public void initWithDoubleBrace() {
        List<Integer> arrayList = new ArrayList<>(){{
            add(3);
            add(1);
            add(2);
        }};

        assertThat(arrayList).contains(1, 2, 3);
    }

    @Test
    public void initWithCapacity() {
        List<Integer> arrayList = new ArrayList<>(1000);

        for (int i = 0; i < 1000; i++) {
            arrayList.add(i);
        }

        assertThat(arrayList).hasSize(1000);
    }
}
