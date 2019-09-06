package com.hellokoding.java.collections;


import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class ArrayListExample {
    public static void main(String[] args) {
        // create and initialize
        List<Integer> lst = new ArrayList<>();
        lst.add(3);
        lst.add(1);
        lst.add(2);
        System.out.println(lst);

        // iterate with Java 8+ forEach(Consumer)
        lst.forEach(e -> System.out.printf("%d ", e));
        System.out.println();

        // sort in ascending
        lst.sort(Comparator.naturalOrder());
        System.out.println(lst);
        // sort in descending
        lst.sort(Comparator.reverseOrder());
        System.out.println(lst);

        // query
        System.out.println(lst.get(0)); // get element by index
        System.out.println(lst.indexOf(3)); // get index by element
        System.out.println(lst.contains(2));
        System.out.println(lst.size());
        System.out.println(lst.isEmpty());

        // update an element at index
        lst.set(0, 10);
        System.out.println(lst);

        // delete an element by index
        lst.remove(1);
        System.out.println(lst);
        // delete an element by value
        lst.remove(Integer.valueOf(10));
        System.out.println(lst);
    }
}