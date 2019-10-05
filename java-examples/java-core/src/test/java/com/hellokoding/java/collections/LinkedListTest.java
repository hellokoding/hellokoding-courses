package com.hellokoding.java.collections;

import org.junit.Test;

import java.util.*;

import static org.assertj.core.api.Assertions.assertThat;

public class LinkedListTest {
    @Test
    public void declare() {
        List<Integer> list = new LinkedList<>();
        assertThat(list).isInstanceOf(LinkedList.class);

        Queue<Integer> queue = new LinkedList<>();
        assertThat(queue).isInstanceOf(LinkedList.class);

        Deque<Integer> stackOrDeque = new LinkedList<>();
        assertThat(stackOrDeque).isInstanceOf(LinkedList.class);

        LinkedList<Integer> linkedList = new LinkedList<>();
    }

    @Test
    public void initInOneLine() {
        LinkedList<Integer> linkedList1 = new LinkedList<>(Arrays.asList(1, 2, 3));
        assertThat(linkedList1).hasSize(3);

        LinkedList<Integer> linkedList2 = new LinkedList<>(List.of(1, 2, 3));
        assertThat(linkedList2).hasSize(3);
    }

    @Test
    public void initInOneLineWithDoubleBrace() {
        LinkedList<Integer> linkedList = new LinkedList<>(){{
            add(1);
            add(2);
            add(3);
        }};

        assertThat(linkedList).hasSize(3);
    }

    @Test
    public void initWithAddAll() {
        LinkedList<Integer> linkedList = new LinkedList<>();
        linkedList.add(1);
        linkedList.add(2);
        linkedList.addAll(List.of(4, 5, 6));

        assertThat(linkedList).hasSize(5);
    }

    @Test
    public void iterateWithForEachConsumer() {
        LinkedList<Integer> linkedList = new LinkedList<>(List.of(1, 2, 3));
        linkedList.forEach(e -> System.out.printf("%d ", e));
    }

    @Test
    public void iterateWithIterator() {
        LinkedList<Integer> linkedList = new LinkedList<>(List.of(1, 2, 3));

        Iterator<Integer> iterator = linkedList.iterator();
        while (iterator.hasNext()){
            System.out.printf("%d ", iterator.next());
        }
    }

    @Test
    public void retrieving() {
        LinkedList<Integer> linkedList = new LinkedList<>(List.of(1, 2, 3));

        int firstElement = linkedList.getFirst();
        assertThat(firstElement).isEqualTo(1);

        int lastElement = linkedList.getLast();
        assertThat(lastElement).isEqualTo(3);
    }

    @Test
    public void adding() {
        LinkedList<Integer> linkedList = new LinkedList<>(List.of(1, 2, 3));

        linkedList.addFirst(0);
        assertThat(linkedList).containsExactly(0, 1, 2, 3);

        linkedList.addLast(4);
        assertThat(linkedList).containsExactly(0, 1, 2, 3, 4);
    }

    @Test
    public void removing() {
        LinkedList<Integer> linkedList = new LinkedList<>(List.of(1, 2, 3));

        linkedList.removeFirst();
        assertThat(linkedList).containsExactly(2, 3);

        linkedList.removeLast();
        assertThat(linkedList).containsExactly(2);
    }
}
