package com.hellokoding.java.collections;

import org.junit.Test;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;
import java.util.Spliterator;

public class HashSetIterationTest {
    @Test
    public void iterateWithForEachConsumer() {
        Set<Integer> set = new HashSet<>(Set.of(3, 1, 2));

        set.forEach(ele -> System.out.printf("%d ", ele));
    }

    @Test
    public void iterateWithForEach() {
        Set<Integer> set = new HashSet<>(Set.of(3, 1, 2));

        for (int ele : set) {
            System.out.printf("%d ", ele);
        }
    }

    @Test
    public void iterateWithIterator() {
        Set<Integer> set = new HashSet<>(Set.of(3, 1, 2));
        Iterator<Integer> iterator = set.iterator();

        while (iterator.hasNext()) {
            System.out.printf("%d ", iterator.next());
        }
    }

    @Test
    public void iterateWithSplitIterator() {
        Set<Integer> set = new HashSet<>(Set.of(3, 1, 2));
        Spliterator<Integer> spliterator = set.spliterator();

        spliterator.tryAdvance(ele -> System.out.printf("%d ", ele));
    }
}
