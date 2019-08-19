package com.hellokoding.java.collections;

import org.junit.Test;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;

public class HashSetFindElementTest {
    @Test
    public void findWithStreamAPI() {
        Set<Integer> set = new HashSet<>();
        set.add(3);
        set.add(1);
        set.add(2);

        Integer obj = set.stream()
            .filter(ele -> ele.equals(1))
            .findFirst()
            .orElse(null);
        assertThat(obj).isEqualTo(1);
    }

    @Test
    public void findWithForEachConsumer() {
        Set<Integer> set = new HashSet<>();
        set.add(3);
        set.add(1);
        set.add(2);

        set.forEach(ele -> {
            if (ele.equals(1)) {
                System.out.println(ele);
            }
        });
    }

    @Test
    public void findWithForEach() {
        Set<Integer> set = new HashSet<>();
        set.add(3);
        set.add(1);
        set.add(2);

        for (Integer ele : set) {
            if (ele.equals(1)) {
                System.out.println(ele);
            }
        }
    }

    @Test
    public void findWithIterator() {
        Set<Integer> set = new HashSet<>();
        set.add(3);
        set.add(1);
        set.add(2);

        Iterator<Integer> iterator = set.iterator();
        while (iterator.hasNext()) {
            Integer obj = iterator.next();
            if (obj.equals(1)) {
                System.out.println(obj);
            }
        }
    }

    @Test
    public void contains() {
        Set<Integer> set = new HashSet<>();
        set.add(3);
        set.add(1);
        set.add(2);

        boolean result = set.contains(1);
        assertThat(result).isTrue();

        result = set.contains(4);
        assertThat(result).isFalse();
    }

    @Test
    public void containsAll() {
        Set<Integer> set = new HashSet<>();
        set.add(3);
        set.add(1);
        set.add(2);

        boolean result = set.containsAll(Set.of(1, 2));
        assertThat(result).isTrue();

        result = set.containsAll(Set.of(1, 4));
        assertThat(result).isFalse();
    }

    @Test
    public void size() {
        Set<Integer> set = new HashSet<>();
        set.add(3);
        set.add(1);
        set.add(2);

        int size = set.size();
        assertThat(size).isEqualTo(3);
    }

    @Test
    public void isEmpty() {
        Set<Integer> set = new HashSet<>();
        set.add(3);
        set.add(1);
        set.add(2);

        boolean result = set.isEmpty();
        assertThat(result).isFalse();
    }
}
