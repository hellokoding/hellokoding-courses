package com.hellokoding.java.collections;


import java.util.*;

public class HashSetExample {
    public static void main(String[] args) {
        // create and initialize
        Set<Integer> set = new HashSet<>();
        set.add(3);
        set.add(1);
        set.add(2);
        System.out.println(set);

        // iterate with Java 8+ forEach(Consumer)
        set.forEach(e -> System.out.printf("%d ", e));
        System.out.println();

        // sort
        Set<Integer> treeSet = new TreeSet<>(set);
        System.out.println(treeSet);

        // query
        Integer obj = set.stream()
                .filter(ele -> ele.equals(1))
                .findFirst()
                .orElse(null);
        System.out.println(obj);
        System.out.println(set.contains(2));
        System.out.println(set.size());
        System.out.println(set.isEmpty());

        // delete an element
        set.remove(1);
        System.out.println(set);

        // update = delete + add
        set.remove(2);
        set.add(20);
        System.out.println(set);
    }
}
