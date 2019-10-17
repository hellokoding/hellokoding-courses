package com.hellokoding.java.collections;

import org.junit.Test;

import java.util.*;

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
        Map.Entry<String, Integer> e2 = Map.entry("k2", 2);
        Map.Entry<String, Integer> e3 = Map.entry("k3", 3);

        Map<String, Integer> map = new HashMap<>(Map.ofEntries(e3, e1, e2));

        NavigableMap<String, Integer> treeMap1 = new TreeMap<>(Comparator.reverseOrder());
        treeMap1.putAll(map);
        assertThat(treeMap1).containsExactly(e3, e2, e1);

        NavigableMap<String, Integer> treeMap2 = treeMap1.descendingMap();
        assertThat(treeMap2).containsExactly(e1, e2, e3);
    }

    @Test
    public void sortByKeys_WithTreeMapAndCustomComparator() {
        Category c1 = new Category(1, "c1");
        Category c2 = new Category(2, "c2");
        Category c3 = new Category(3, "c2");

        Book b1 = new Book(1, "b1");
        Book b2 = new Book(2, "b2");
        Book b3 = new Book(3, "b3");

        Map.Entry<Category, Book> cb1 = Map.entry(c1, b1);
        Map.Entry<Category, Book> cb2 = Map.entry(c2, b2);
        Map.Entry<Category, Book> cb3 = Map.entry(c3, b3);

        Map<Category, Book> map = new HashMap<>(Map.ofEntries(cb3, cb1, cb2));

        Comparator<Category> c = Comparator
            .comparing(Category::getName, Comparator.reverseOrder())
            .thenComparing(Category::getId, Comparator.reverseOrder());

        NavigableMap<Category, Book> treeMap1 = new TreeMap<>(c);
        treeMap1.putAll(map);
        assertThat(treeMap1).containsExactly(cb3, cb2, cb1);

        NavigableMap<Category, Book> treeMap2 = treeMap1.descendingMap();
        assertThat(treeMap2).containsExactly(cb1, cb2, cb3);
    }

    @Test
    public void sortByKeysOrValues_WithTreeSet() {
        Map.Entry<String, Integer> e1 = Map.entry("k1", 1);
        Map.Entry<String, Integer> e2 = Map.entry("k2", 2);
        Map.Entry<String, Integer> e3 = Map.entry("k3", 3);

        Map<String, Integer> map = new HashMap<>(Map.ofEntries(e3, e1, e2));

        NavigableSet<Map.Entry<String, Integer>> treeSet1 = new TreeSet<>(Map.Entry.comparingByKey());
        treeSet1.addAll(map.entrySet());
        assertThat(treeSet1).containsExactly(e1, e2, e3);

        Map<String, Integer> linkedHashMap = new LinkedHashMap<>();
        treeSet1.forEach(e -> linkedHashMap.put(e.getKey(), e.getValue()));
        assertThat(linkedHashMap).containsExactly(e1, e2, e3);

        NavigableSet<Map.Entry<String, Integer>> treeSet2 = new TreeSet<>(Map.Entry.comparingByValue());
        treeSet2.addAll(map.entrySet());
        assertThat(treeSet2).containsExactly(e1, e2, e3);

        Map<String, Integer> linkedHashMap2 = new LinkedHashMap<>();
        treeSet2.forEach(e -> linkedHashMap2.put(e.getKey(), e.getValue()));
        assertThat(linkedHashMap2).containsExactly(e1, e2, e3);
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
