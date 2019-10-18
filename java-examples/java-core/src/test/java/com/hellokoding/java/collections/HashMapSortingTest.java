package com.hellokoding.java.collections;

import org.junit.Test;

import java.util.*;

import static java.util.Map.Entry.comparingByKey;
import static java.util.Map.Entry.comparingByValue;
import static org.assertj.core.api.Assertions.assertThat;

public class HashMapSortingTest {
    @Test
    public void sortByKeys_WithTreeMapAndComparable() {
        Map.Entry<String, Integer> e1 = Map.entry("k1", 1);
        Map.Entry<String, Integer> e2 = Map.entry("k2", 2);
        Map.Entry<String, Integer> e3 = Map.entry("k3", 3);

        Map<String, Integer> map = new HashMap<>(Map.ofEntries(e3, e1, e2));

        NavigableMap<String, Integer> treeMap1 = new TreeMap<>(map);
        assertThat(treeMap1).containsExactly(e1, e2, e3);

        NavigableMap<String, Integer> treeMap2 = treeMap1.descendingMap();
        assertThat(treeMap2).containsExactly(e3, e2, e1);
    }

    @Test
    public void sortByKeys_WithTreeMapAndComparator() {
        Map.Entry<String, Integer> e1 = Map.entry("k1", 1);
        Map.Entry<String, Integer> e2 = Map.entry("k02", 2);
        Map.Entry<String, Integer> e3 = Map.entry("k3", 3);

        Map<String, Integer> map = new HashMap<>(Map.ofEntries(e3, e1, e2));

        NavigableMap<String, Integer> treeMap1 = new TreeMap<>(Comparator.reverseOrder());
        treeMap1.putAll(map);
        assertThat(treeMap1).containsExactly(e3, e1, e2);

        NavigableMap<String, Integer> treeMap2 = treeMap1.descendingMap();
        assertThat(treeMap2).containsExactly(e2, e1, e3);
    }

    @Test
    public void sortByKeys_WithTreeMapAndCustomComparator() {
        Category c1 = new Category(1, "c1");
        Category c2 = new Category(20, "c2");
        Category c3 = new Category(3, "c2");

        Book b1 = new Book(1, "b1");
        Book b2 = new Book(20, "b2");
        Book b3 = new Book(3, "b2");

        Map.Entry<Category, Book> cb1 = Map.entry(c1, b1);
        Map.Entry<Category, Book> cb2 = Map.entry(c2, b2);
        Map.Entry<Category, Book> cb3 = Map.entry(c3, b3);

        Map<Category, Book> map = new HashMap<>(Map.ofEntries(cb3, cb1, cb2));

        Comparator<Category> c = Comparator
            .comparing(Category::getName, Comparator.reverseOrder())
            .thenComparing(Category::getId, Comparator.reverseOrder());

        NavigableMap<Category, Book> treeMap1 = new TreeMap<>(c);
        treeMap1.putAll(map);
        assertThat(treeMap1).containsExactly(cb2, cb3, cb1);

        NavigableMap<Category, Book> treeMap2 = treeMap1.descendingMap();
        assertThat(treeMap2).containsExactly(cb1, cb3, cb2);
    }

    @Test
    public void sortByKeysAndByValues_WithTreeSetAndComparable() {
        Map.Entry<String, Integer> e1 = Map.entry("k1", 1);
        Map.Entry<String, Integer> e2 = Map.entry("k2", 20);
        Map.Entry<String, Integer> e3 = Map.entry("k3", 3);

        Map<String, Integer> map = new HashMap<>(Map.ofEntries(e3, e1, e2));

        NavigableSet<Map.Entry<String, Integer>> treeSet1 = new TreeSet<>(comparingByKey());
        treeSet1.addAll(map.entrySet());
        assertThat(treeSet1).containsExactly(e1, e2, e3);

        NavigableSet<Map.Entry<String, Integer>> treeSet2 = new TreeSet<>(comparingByValue());
        treeSet2.addAll(map.entrySet());
        assertThat(treeSet2).containsExactly(e1, e3, e2);
    }

    @Test
    public void sortByKeysAndByValues_WithTreeSetAndComparator() {
        Map.Entry<String, Integer> e1 = Map.entry("k1", 1);
        Map.Entry<String, Integer> e2 = Map.entry("k2", 20);
        Map.Entry<String, Integer> e3 = Map.entry("k3", 3);

        Map<String, Integer> map = new HashMap<>(Map.ofEntries(e3, e1, e2));

        NavigableSet<Map.Entry<String, Integer>> treeSet1 = new TreeSet<>(comparingByKey(Comparator.reverseOrder()));
        treeSet1.addAll(map.entrySet());
        assertThat(treeSet1).containsExactly(e3, e2, e1);

        NavigableSet<Map.Entry<String, Integer>> treeSet2 = new TreeSet<>(comparingByValue(Comparator.reverseOrder()));
        treeSet2.addAll(map.entrySet());
        assertThat(treeSet2).containsExactly(e2, e3, e1);
    }

    @Test
    public void sortByKeysAndByValues_WithTreeSetAndCustomComparator() {
        Category c1 = new Category(1, "c1");
        Category c2 = new Category(20, "c2");
        Category c3 = new Category(3, "c2");

        Book b1 = new Book(1, "b1");
        Book b2 = new Book(20, "b2");
        Book b3 = new Book(3, "b2");

        Map.Entry<Category, Book> cb1 = Map.entry(c1, b1);
        Map.Entry<Category, Book> cb2 = Map.entry(c2, b2);
        Map.Entry<Category, Book> cb3 = Map.entry(c3, b3);

        Map<Category, Book> map = new HashMap<>(Map.ofEntries(cb3, cb1, cb2));

        Comparator<Category> categoryComparator = Comparator
            .comparing(Category::getName, Comparator.reverseOrder())
            .thenComparing(Category::getId, Comparator.reverseOrder());

        NavigableSet<Map.Entry<Category, Book>> treeSet1 = new TreeSet<>(comparingByKey(categoryComparator));
        treeSet1.addAll(map.entrySet());
        assertThat(treeSet1).containsExactly(cb2, cb3, cb1);

        Comparator<Book> bookComparator = Comparator
            .comparing(Book::getTitle, Comparator.reverseOrder())
            .thenComparing(Book::getId, Comparator.reverseOrder());

        NavigableSet<Map.Entry<Category, Book>> treeSet2 = new TreeSet<>(comparingByValue(bookComparator));
        treeSet2.addAll(map.entrySet());
        assertThat(treeSet2).containsExactly(cb2, cb3, cb1);
    }

    @Test
    public void sortByKeysAndByValues_WithArrayListAndComparator() {
        Map.Entry<String, Integer> e1 = Map.entry("k1", 1);
        Map.Entry<String, Integer> e2 = Map.entry("k2", 20);
        Map.Entry<String, Integer> e3 = Map.entry("k3", 3);

        Map<String, Integer> map = new HashMap<>(Map.ofEntries(e3, e1, e2));

        List<Map.Entry<String, Integer>> arrayList1 = new ArrayList<>(map.entrySet());
        arrayList1.sort(comparingByKey(Comparator.naturalOrder()));
        assertThat(arrayList1).containsExactly(e1, e2, e3);

        List<Map.Entry<String, Integer>> arrayList2 = new ArrayList<>(map.entrySet());
        arrayList2.sort(comparingByValue(Comparator.reverseOrder()));
        assertThat(arrayList2).containsExactly(e2, e3, e1);
    }

    @Test
    public void sortByKeysAndByValues_WithArrayListAndCustomComparator() {
        Category c1 = new Category(1, "c1");
        Category c2 = new Category(20, "c2");
        Category c3 = new Category(3, "c2");

        Book b1 = new Book(1, "b1");
        Book b2 = new Book(20, "b2");
        Book b3 = new Book(3, "b2");

        Map.Entry<Category, Book> cb1 = Map.entry(c1, b1);
        Map.Entry<Category, Book> cb2 = Map.entry(c2, b2);
        Map.Entry<Category, Book> cb3 = Map.entry(c3, b3);

        Map<Category, Book> map = new HashMap<>(Map.ofEntries(cb3, cb1, cb2));

        Comparator<Category> categoryComparator = Comparator
            .comparing(Category::getName, Comparator.reverseOrder())
            .thenComparing(Category::getId, Comparator.reverseOrder());

        List<Map.Entry<Category, Book>> arrayList1 = new ArrayList<>(map.entrySet());
        arrayList1.sort(comparingByKey(categoryComparator));
        assertThat(arrayList1).containsExactly(cb2, cb3, cb1);

        Comparator<Book> bookComparator = Comparator
            .comparing(Book::getTitle, Comparator.reverseOrder())
            .thenComparing(Book::getId, Comparator.reverseOrder());

        List<Map.Entry<Category, Book>> arrayList2 = new ArrayList<>(map.entrySet());
        arrayList2.sort(comparingByValue(bookComparator));
        assertThat(arrayList2).containsExactly(cb2, cb3, cb1);
    }

    static class Category {
        int id;
        String name;

        Category(int id, String name) {
            this.id = id;
            this.name = name;
        }

        int getId() { return id;}

        String getName() { return name;}
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
