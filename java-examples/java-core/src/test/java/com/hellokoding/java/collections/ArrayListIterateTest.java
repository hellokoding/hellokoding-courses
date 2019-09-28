package com.hellokoding.java.collections;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class ArrayListIterateTest {
    @Test
    public void forIndex() {
        List<Integer> lst = new ArrayList<>();
        lst.add(1);
        lst.add(2);
        lst.add(3);

        for(int i=0; i<lst.size(); i++){
            int ele = lst.get(i);
            System.out.println(ele);
        }
    }

    @Test
    public void forEach() {
        List<Integer> lst = new ArrayList<>();
        lst.add(1);
        lst.add(2);
        lst.add(3);

        for(int ele : lst){
            System.out.println(ele);
        }
    }

    @Test
    public void forEachConsumer() {
        List<Integer> lst = new ArrayList<>();
        lst.add(1);
        lst.add(2);
        lst.add(3);

        lst.forEach(e -> System.out.printf("%d ", e));
    }

    @Test
    public void iterator() {
        List<Integer> lst = new ArrayList<>();
        lst.add(1);
        lst.add(2);
        lst.add(3);

        Iterator<Integer> iterator = lst.iterator();
        while (iterator.hasNext()){
            System.out.println(iterator.next());
        }
    }
}
