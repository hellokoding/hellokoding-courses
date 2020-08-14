package com.hellokoding.java.collections;

import org.junit.Test;

import java.util.*;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class ArrayListTest {
    @Test
    public void declare() {
        List<Integer> lst1 = new ArrayList<>();
        assertThat(lst1).isInstanceOf(ArrayList.class);

        ArrayList<Integer> lst2 = new ArrayList<>();
    }

    @Test
    public void initWithListOfAndSetOf() {
        List<Integer> arrayList1 = new ArrayList<>(List.of(3, 1, 2));
        assertThat(arrayList1).contains(3, 1, 2);

        List<Integer> arrayList2 = new ArrayList<>(Set.of(5, 4, 6));
        assertThat(arrayList2).contains(5, 4, 6);
    }

    @Test
    public void initWithAdd() {
        // Create a new ArrayList
        List<Integer> lst = new ArrayList<>();

        // Add elements to ArrayList
        lst.add(3);
        lst.add(1);
        lst.add(2);

        // Can add null element
        lst.add(null);

        // Can add duplicate element
        lst.add(2);

        assertThat(lst).hasSize(5);

        // The output ordering will be same as the insertion oder
        System.out.println(lst);
    }

    @Test
    public void forEachConsumer() {
        List<Integer> lst = new ArrayList<>(List.of(1, 2, 3));

        lst.forEach(e -> System.out.printf("%d ", e));
    }

    @Test
    public void get() {
        List<Integer> lst = new ArrayList<>(List.of(1, 2, 3));

        int firstElement = lst.get(0);
        assertThat(firstElement).isEqualTo(1);
    }

    @Test
    public void filterAndRetrieve() {
        List<Integer> lst = new ArrayList<>(List.of(1, 2, 3));

        Integer firstElement = lst.stream().findFirst().orElse(null);
        assertThat(firstElement).isEqualTo(1);

        Integer[] arr = lst.stream().filter(e -> e >= 2).toArray(Integer[]::new);
        assertThat(arr).contains(2, 3);
    }

    @Test
    public void insert() {
        List<Integer> lst = new ArrayList<>();
        lst.add(1);
        lst.add(2);

        // Inserts element to lst at the specified index
        lst.add(0, 3);

        // Asserts that the list contains exactly the given values in order
        assertThat(lst).containsExactly(3, 1, 2);

        // Asserts that the list throws IndexOutOfBoundsException
        //     when insert element at the out of bound position
        assertThatThrownBy(() -> lst.add(5, 4))
                .isInstanceOf(IndexOutOfBoundsException.class);
    }

    @Test
    public void containsAddUpdateRemoveSingleElement() {
        List<Integer> arrayList = new ArrayList<>(List.of(1, 2, 3));
        Boolean contained = arrayList.contains(3);
        assertThat(contained).isTrue();

        arrayList.add(4); // appends an element
        arrayList.add(0, 10); // inserts at index 0
        arrayList.add(null);
        assertThat(arrayList).containsExactly(10, 1, 2, 3, 4, null);

        arrayList.set(0, 100); // update at index 0
        assertThat(arrayList).containsExactly(100, 1, 2, 3, 4, null);

        arrayList.remove(1); // removes at index 1
        assertThat(arrayList).containsExactly(100, 2, 3, 4, null);

        arrayList.remove(Integer.valueOf(2)); // removes an element
        assertThat(arrayList).containsExactly(100, 3, 4, null);
    }

    @Test
    public void containsAddUpdateRemoveMultipleElements() {
        List<Integer> arrayList = new ArrayList<>(List.of(1, 2, 3));
        Boolean contained = arrayList.containsAll(List.of(1, 2));
        assertThat(contained).isTrue();

        arrayList.addAll(List.of(4, 5));
        arrayList.addAll(0, List.of(10, 100));
        assertThat(arrayList).containsExactly(10, 100, 1, 2, 3, 4, 5);

        arrayList.replaceAll(e -> ++e);
        assertThat(arrayList).containsExactly(11, 101, 2, 3, 4, 5, 6);

        arrayList.removeAll(List.of(1, 11, 101));
        assertThat(arrayList).containsExactly(2, 3, 4, 5, 6);

        arrayList.removeIf(e -> e >= 3);
        assertThat(arrayList).contains(2);
    }

    @Test
    public void objectsComparingProblem(){
        List<Book> list = new ArrayList<>();
        list.add(new Book(1, "a"));

        Boolean contained = list.contains(new Book(1, "a"));
        assertThat(contained).isFalse();

        list.remove(new Book(1, "a"));
        assertThat(list).hasSize(1);
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
        List<BookFixed> list = new ArrayList<>();
        list.add(new BookFixed(1, "a"));

        Boolean contained = list.contains(new BookFixed(1, "a"));
        assertThat(contained).isTrue();

        list.remove(new BookFixed(1, "a"));
        assertThat(list).hasSize(0);
    }

    static class BookFixed {
        private int id;
        private String title;

        public BookFixed(int id, String title) {
            this.id = id;
            this.title = title;
        }

        public int getId() {
            return id;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            BookFixed bookFixed = (BookFixed) o;
            return id == bookFixed.id &&
                Objects.equals(title, bookFixed.title);
        }

        @Override
        public int hashCode() {
            return Objects.hash(id, title);
        }
    }
}
