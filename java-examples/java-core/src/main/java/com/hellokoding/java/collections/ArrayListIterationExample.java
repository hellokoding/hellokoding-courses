package com.hellokoding.java.collections;

import java.util.*;

public class ArrayListIterationExample {
    public static void main(String[] args) {
        List<Integer> arrayList1 = new ArrayList<>(List.of(1, 2, 3));

        // Iterate forward with for-index loop
        for(int i=0; i<arrayList1.size(); i++){
            int ele = arrayList1.get(i);
            System.out.printf("%d ", ele); // 1 2 3
        }

        System.out.println();

        // Iterate backward with for-index loop
        for(int i=arrayList1.size()-1; i>=0; i--){
            int ele = arrayList1.get(i);
            System.out.printf("%d ", ele); // 3 2 1
        }

        System.out.println();

        // Iterate forward with for-each loop
        for(int ele : arrayList1){
            System.out.printf("%d ", ele); // 1 2 3
        }

        System.out.println();

        // Iterate forward with forEach(Consumer) method
        arrayList1.forEach(e -> System.out.printf("%d ", e)); // 1 2 3

        System.out.println();

        // Iterate forward with iterator() method
        Iterator<Integer> iterator = arrayList1.iterator();
        while (iterator.hasNext()){
            System.out.printf("%d ", iterator.next()); // 1 2 3
        }

        System.out.println();

        // Iterate backward with listIterator() method
        ListIterator<Integer> listIterator = arrayList1.listIterator(arrayList1.size());
        while (listIterator.hasPrevious()){
            System.out.printf("%d ", listIterator.previous()); // 3 2 1
        }
    }
}
