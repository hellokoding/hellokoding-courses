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
    public void initWithFactoryMethodsInOneLine() {
        LinkedList<Integer> linkedList1 = new LinkedList<>(List.of(3, 1, 2, 2));
        assertThat(linkedList1).containsExactly(3, 1, 2, 2);

        LinkedList<Integer> linkedList2 = new LinkedList<>(Set.of(1, 2, 3));
        assertThat(linkedList2).contains(1, 2, 3);

        LinkedList<Integer> linkedList3 = new LinkedList<>(Arrays.asList(3, 1, 2, 2, null));
        assertThat(linkedList3).containsExactly(3, 1, 2, 2, null);
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
        linkedList.add(2); // can add duplicated element
        linkedList.add(null); // add null element
        linkedList.addAll(List.of(4, 5, 6));

        // The iteration order is same as the insertion order
        assertThat(linkedList).containsExactly(1, 2, 2, null, 4, 5, 6);
    }

    @Test
    public void iterateWithForEachConsumer() {
        LinkedList<Integer> linkedList = new LinkedList<>(List.of(1, 2, 3));
        linkedList.forEach(e -> System.out.printf("%d ", e));
    }

    @Test
    public void iterateWithIteratorAndListIterator() {
        LinkedList<Integer> linkedList = new LinkedList<>(List.of(1, 2, 3));

        // Iterate forward with Iterator
        Iterator<Integer> iterator = linkedList.iterator();
        while (iterator.hasNext()){
            System.out.printf("%d ", iterator.next());
        }

        // Iterate backward with ListIterator
        ListIterator<Integer> listIterator = linkedList.listIterator(linkedList.size());
        while (listIterator.hasPrevious()){
            System.out.printf("%d ", listIterator.previous());
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
    public void filterAndRetrieve() {
        LinkedList<Integer> linkedList = new LinkedList<>(List.of(1, 2, 3));

        Integer firstElement = linkedList.stream().findFirst().orElse(null);
        assertThat(firstElement).isEqualTo(1);

        Integer[] arr = linkedList.stream().filter(e -> e >= 2).toArray(Integer[]::new);
        assertThat(arr).contains(2, 3);
    }

    @Test
    public void addAndRemove() {
        LinkedList<Integer> linkedList = new LinkedList<>(List.of(1, 2, 3));

        linkedList.addFirst(0);
        assertThat(linkedList).containsExactly(0, 1, 2, 3);

        linkedList.addLast(4);
        assertThat(linkedList).containsExactly(0, 1, 2, 3, 4);

        linkedList.removeFirst();
        assertThat(linkedList).containsExactly(1, 2, 3, 4);

        linkedList.removeLast();
        assertThat(linkedList).containsExactly(1, 2, 3);
    }

    @Test
    public void examineAndUpdate() {
        LinkedList<Integer> linkedList = new LinkedList<>(List.of(1, 2, 3));

        boolean contained = linkedList.contains(1);
        assertThat(contained).isTrue();

        linkedList.set(1, 20);
        assertThat(linkedList).containsExactly(1, 20 , 3);
    }

    @Test
    public void objectsComparingProblem(){
        LinkedList<Book> linkedList = new LinkedList<>();
        linkedList.add(new Book(1, "a"));

        Boolean contained = linkedList.contains(new Book(1, "a"));
        assertThat(contained).isFalse();

        linkedList.remove(new Book(1, "a"));
        assertThat(linkedList).hasSize(1);
    }

    static class Book {
        int id;
        String title;

        Book(int id, String title) {
            this.id = id;
            this.title = title;
        }

        int getId() {
            return id;
        }

        String getTitle() {
            return title;
        }
    }

    @Test
    public void objectsComparingFixed(){
        LinkedList<BookFixed> linkedList = new LinkedList<>();
        linkedList.add(new BookFixed(1, "a"));

        Boolean contained = linkedList.contains(new BookFixed(1, "a"));
        assertThat(contained).isTrue();

        linkedList.remove(new BookFixed(1, "a"));
        assertThat(linkedList).hasSize(0);
    }

    static class BookFixed {
        int id;
        String title;

        BookFixed(int id, String title) {
            this.id = id;
            this.title = title;
        }

        int getId() {
            return id;
        }

        String getTitle() {
            return title;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            BookFixed bookFixed = (BookFixed) o;
            return id == bookFixed.id &&
                    Objects.equals(title, bookFixed.title);
        }
    }

    @Test
    public void sortMultipleFields() {
        Book book1 = new Book(1, "b");
        Book book2 = new Book(2, "c");
        Book book3 = new Book(3, "c");

        LinkedList<Book> list = new LinkedList<>(Arrays.asList(book1, book2, book3));
        list.sort(Comparator
                .comparing(Book::getTitle, Comparator.reverseOrder())
                .thenComparing(Book::getId, Comparator.reverseOrder()));

        assertThat(list).containsExactly(book3, book2, book1);
    }
}
