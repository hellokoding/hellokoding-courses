package com.hellokoding.java.collections;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

public class ArrayListIterateTest {
    @Test
    public void forEachConsumer() {
        List<Integer> lst = new ArrayList<>(List.of(1, 2, 3));

        lst.forEach(e -> System.out.printf("%d ", e));
    }

    @Test
    public void forEach() {
        List<Integer> lst = new ArrayList<>(List.of(1, 2, 3));

        for(int ele : lst){
            System.out.printf("%d ", ele);
        }
    }

    @Test
    public void forIndexForward() {
        List<Integer> lst = new ArrayList<>(List.of(1, 2, 3));

        for(int i=0; i<lst.size(); i++){
            int ele = lst.get(i);
            System.out.printf("%d ", ele);
        }
    }

    @Test
    public void forIndexBackward() {
        List<Integer> lst = new ArrayList<>(List.of(1, 2, 3));

        for(int i=lst.size()-1; i>=0; i--){
            int ele = lst.get(i);
            System.out.printf("%d ", ele);
        }
    }

    @Test
    public void iterator() {
        List<Integer> lst = new ArrayList<>(List.of(1, 2, 3));

        Iterator<Integer> iterator = lst.iterator();
        while (iterator.hasNext()){
            System.out.printf("%d ", iterator.next());
        }
    }

    @Test
    public void listIterator() {
        List<Integer> lst = new ArrayList<>(List.of(1, 2, 3));

        ListIterator<Integer> iterator = lst.listIterator(lst.size());
        while (iterator.hasPrevious()){
            System.out.printf("%d ", iterator.previous());
        }
    }
}
