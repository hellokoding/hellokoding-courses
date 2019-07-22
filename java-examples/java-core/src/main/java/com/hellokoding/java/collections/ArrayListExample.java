package com.hellokoding.java.collections;


import java.util.*;

public class ArrayListExample {
    public static void main(String args[]) {
        // create and initialize
        List<Integer> lst1 = new ArrayList<>();
        lst1.add(1);
        lst1.add(2);
        lst1.add(3);

        List<Integer> lst2 = new ArrayList<>(20);

        List<Integer> lst3 = new ArrayList<>(List.of(1, 2, 3));
        System.out.println(lst3); // [1, 2, 3]

        List<Integer> lst4 = new ArrayList<>(Set.of(1, 2, 3));
        System.out.println(lst4); // [3, 2, 1]

        List<Integer> lst5 = Collections.emptyList();

        List<Integer> lst6 = Collections.singletonList(1);

        List<Integer> lst7 = List.copyOf(lst6);

        // iterate
        lst1.forEach(System.out::println);

        for(int ele : lst1) {
            System.out.printf("%d ", ele);
        }
        System.out.println();

        for (int i = 0; i < lst1.size(); i++) {
            System.out.printf("%d ", lst1.get(i));
        }
        System.out.println();

        Iterator<Integer> iter = lst1.iterator();
        while (iter.hasNext()) {
            System.out.printf("%d ", iter.next());
        }
        System.out.println();

        // add elements into a list
        lst1.add(4);
        lst1.add(5);
        lst1.add(5); // add a duplicate element
        lst1.add(null); // add a null element
        System.out.println(lst1); // [1, 2, 3, 4, 5, 5, null]

        // update an element at index
        lst1.set(0, 10);

        // delete an element by index
        lst1.remove(1);

        // delete an element by value
        lst1.remove(null);


        // get an element by index
        int i1 = lst1.get(0);

        // check if an element existing
        boolean isExist = lst1.contains(3);

        // check the list size
        int size = lst1.size();

        // check if the list is empty
        boolean isEmpty = lst1.isEmpty();

        // sort a list in ascending order
        // NullPointerException will be thrown if the list has null elements
        lst1.sort(Comparator.naturalOrder());
        Collections.sort(lst1);
        System.out.println(lst1);

        // sort a list in descending order
        lst1.sort(Comparator.reverseOrder());
        Collections.sort(lst1, Collections.reverseOrder());
        System.out.println(lst1);
    }
}