package com.hellokoding.java.collections;

import org.junit.Test;

import java.util.*;

import static org.assertj.core.api.Assertions.assertThat;

public class HashSetSortingTest {
    @Test
    public void sortWith_TreeSet_And_Comparable() {
        Set<Integer> set = new HashSet<>(Set.of(3, 1, 2));

        NavigableSet<Integer> sortedSet = new TreeSet<>(set);
        assertThat(sortedSet).containsExactly(1, 2, 3);

        NavigableSet<Integer> descSet = sortedSet.descendingSet();
        assertThat(descSet).containsExactly(3, 2, 1);
    }

    @Test
    public void sortWith_TreeSet_And_Comparator() {
        Set<Integer> set = new HashSet<>(Set.of(3, 1, 2));

        NavigableSet<Integer> sortedSet = new TreeSet<>(Comparator.reverseOrder());
        sortedSet.addAll(set);
        assertThat(sortedSet).containsExactly(3, 2, 1);
    }

    @Test
    public void sortWith_TreeSet_And_CustomComparator() {
        Book book1 = new Book(1, "b");
        Book book2 = new Book(2, "c");
        Book book3 = new Book(3, "c");
        Set<Book> set = new HashSet<>(Set.of(book1, book2, book3));

        Comparator<Book> c = Comparator
            .comparing(Book::getTitle, Comparator.reverseOrder())
            .thenComparing(Book::getId, Comparator.reverseOrder());

        NavigableSet<Book> sortedSet = new TreeSet<>(c);
        sortedSet.addAll(set);

        assertThat(sortedSet).containsExactly(book3, book2, book1);
    }

    @Test
    public void sortWith_ArrayList_And_Comparable() {
        Set<String> set = new HashSet<>(Set.of("c", "a", "b"));
        List<String> list = new ArrayList<>(set);

        list.sort(Comparator.naturalOrder());
        assertThat(list).containsExactly("a", "b", "c");

        list.sort(Comparator.reverseOrder());
        assertThat(list).containsExactly("c", "b", "a");
    }

    @Test
    public void sortWith_ArrayList_And_CustomComparator() {
        Book book1 = new Book(1, "b");
        Book book2 = new Book(2, "c");
        Book book3 = new Book(3, "c");
        Set<Book> set = new HashSet<>(Set.of(book1, book2, book3));

        List<Book> list = new ArrayList<>(set);
        list.sort(Comparator
            .comparing(Book::getTitle, Comparator.reverseOrder())
            .thenComparing(Book::getId, Comparator.reverseOrder()));

        assertThat(list).containsExactly(book3, book2, book1);
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
}
