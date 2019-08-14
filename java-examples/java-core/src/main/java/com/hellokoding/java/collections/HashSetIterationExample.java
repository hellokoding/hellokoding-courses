package com.hellokoding.java.collections;

import java.util.Iterator;
import java.util.Set;

public class HashSetIterationExample {
    public void iterateWithForEach() {
        Set<Integer> set = Set.of(3, 1, 2);

        for (int ele : set) {
            System.out.println(ele);
        }
    }

    public void iterateWithIterator() {
        Set<Integer> set = Set.of(3, 1, 2);
        Iterator<Integer> iterator = set.iterator();

        while (iterator.hasNext()) {
            System.out.println(iterator.next());
        }
    }

    public void iterateWithForEachConsumer() {
        Set<Integer> set = Set.of(3, 1, 2);

        set.forEach(ele -> System.out.println(ele));
    }
}
