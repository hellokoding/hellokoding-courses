package com.hellokoding.java.collections;

import org.junit.Test;

import java.util.*;

import static org.assertj.core.api.Assertions.assertThat;

public class TreeMapTest {
    @Test
    public void declare() {
        Map<String, Integer> treeMap1 = new TreeMap<>();
        assertThat(treeMap1).isInstanceOf(TreeMap.class);

        TreeMap<String, Integer> treeMap2 = new TreeMap<>();
    }

    @Test
    public void initWithComparable() {
        Map.Entry<String, Integer> e1 = Map.entry("k1", 1);
        Map.Entry<String, Integer> e2 = Map.entry("k2", 20);
        Map.Entry<String, Integer> e3 = Map.entry("k3", 3);

        // create and initialize a TreeMap with Java 9+ Map.ofEntries
        //     keys are ordered based on their Comparable implementation
        NavigableMap<String, Integer> treeMap1 = new TreeMap<>(Map.ofEntries(e3, e1, e2));

        // the iteration order is based on Comparable of the object key
        assertThat(treeMap1).containsExactly(e1, e2, e3);

        // create and initialize a TreeMap with keys ordering based on Comparable
        NavigableMap<String, Integer> treeMap2 = new TreeMap<>();

        // value is overrode with put method when duplicate key
        treeMap2.put(e1.getKey(), e1.getValue());

        // duplicate key is ignored with putIfAbsent
        treeMap2.putIfAbsent(e1.getKey(), e1.getValue());

        // import all mappings from an existing Map
        treeMap2.putAll(treeMap1);

        // the iteration order is based on Comparable of the object key
        assertThat(treeMap2).containsExactly(e1, e2, e3);
    }

    @Test
    public void initWithComparator() {
        Map.Entry<String, Integer> e1 = Map.entry("k1", 1);
        Map.Entry<String, Integer> e2 = Map.entry("k2", 20);
        Map.Entry<String, Integer> e3 = Map.entry("k3", 3);

        NavigableMap<String, Integer> treeMap = new TreeMap<>(Comparator.reverseOrder());
        treeMap.putAll(Map.ofEntries(e3, e1, e2));

        // the iteration order is in
        //     reverse order of the object key Comparable implementation
        assertThat(treeMap).containsExactly(e3, e2, e1);
    }

    @Test
    public void iterateOverKeyValuePairs() {
        NavigableMap<String, Integer> treeMap = new TreeMap<>(Map.of("k1", 1, "k2", 2));
        treeMap.forEach((k, v) -> System.out.printf("%s=%d ", k, v));
    }

    @Test
    public void iterateOverEntrySetKeySetAndValues() {
        NavigableMap<String, Integer> treeMap = new TreeMap<>(Map.of("k1", 1, "k2", 2));
        treeMap.entrySet().forEach(e -> System.out.printf("%s ", e));
        treeMap.keySet().forEach(k -> System.out.printf("%s ", k));
        treeMap.values().forEach(v -> System.out.printf("%s ", v));
    }

    @Test
    public void retrieve() {
        NavigableMap<String, Integer> treeMap = new TreeMap<>(Map.of("k1", 1, "k2", 2));

        Set<Map.Entry<String, Integer>> entrySet = treeMap.entrySet();
        assertThat(entrySet).contains(Map.entry("k1", 1), Map.entry("k2", 2));

        Set<String> keySet = treeMap.keySet();
        assertThat(keySet).contains("k1", "k2");

        Collection<Integer> values = treeMap.values();
        assertThat(values).contains(1, 2);
    }

    @Test
    public void getValueByKey() {
        NavigableMap<String, Integer> treeMap = new TreeMap<>(Map.of("k1", 1, "k2", 2));
        int value = treeMap.get("k1");

        assertThat(value).isEqualTo(1);
    }

    @Test
    public void filter() {
        NavigableMap<String, Integer> treeMap = new TreeMap<>(Map.of("k1", 1, "k2", 2));
        Integer[] arr = treeMap.values().stream().filter(v -> v > 1).toArray(Integer[]::new);
        assertThat(arr).contains(2);
    }

    @Test
    public void containsPutReplaceRemove() {
        NavigableMap<String, Integer> treeMap = new TreeMap<>(Map.of("k1", 1, "k2", 2));

        boolean containedKey = treeMap.containsKey("k1");
        assertThat(containedKey).isTrue();

        boolean containedValue = treeMap.containsValue(2);
        assertThat(containedValue).isTrue();

        treeMap.put("k3", 3);
        assertThat(treeMap).hasSize(3);

        treeMap.replace("k1", 10);
        assertThat(treeMap).contains(Map.entry("k1", 10), Map.entry("k2", 2), Map.entry("k3", 3));

        treeMap.remove("k3");
        assertThat(treeMap).contains(Map.entry("k1", 10), Map.entry("k2", 2));
    }

    @Test
    public void objectsComparingProblem(){
        NavigableMap<Category, Book> treeMap = new TreeMap<>();

        treeMap.put(new Category(1, "c1"), new Book(1, "b1"));

        boolean containedKey = treeMap.containsKey(new Category(2, "c1"));
        assertThat(containedKey).isTrue();

        boolean containedValue = treeMap.containsValue(new Book(2, "b1"));
        assertThat(containedValue).isTrue();

        treeMap.put(new Category(2, "c1"), new Book(2, "b1"));
        assertThat(treeMap).hasSize(1);

        Book previousValue = treeMap.replace(new Category(2, "c1"), new Book(2, "b1"));
        assertThat(previousValue).isNotNull();

        treeMap.remove(new Category(2, "c1"));
        assertThat(treeMap).hasSize(0);
    }

    static class Category implements Comparable<Category> {
        int id;
        String name;

        Category(int id, String name) {
            this.id = id;
            this.name = name;
        }

        @Override
        public int compareTo(Category o) {
            return CharSequence.compare(this.name, o.name);
        }
    }

    static class Book  implements Comparable<Book> {
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

        @Override
        public int compareTo(Book o) {
            return CharSequence.compare(this.title, o.title);
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Book book = (Book) o;
            return Objects.equals(title, book.title);
        }
    }

    @Test
    public void objectsComparingFixed(){
        NavigableMap<CategoryFixed, BookFixed> treeMap = new TreeMap<>();

        treeMap.put(new CategoryFixed(1, "c1"), new BookFixed(1, "b1"));

        boolean containedKey = treeMap.containsKey(new CategoryFixed(2, "c1"));
        assertThat(containedKey).isFalse();

        boolean containedValue = treeMap.containsValue(new BookFixed(2, "b1"));
        assertThat(containedValue).isFalse();

        treeMap.put(new CategoryFixed(2, "c1"), new BookFixed(2, "b1"));
        assertThat(treeMap).hasSize(2);

        BookFixed previousValue = treeMap.replace(new CategoryFixed(2, "c1"), new BookFixed(2, "b1"));
        assertThat(previousValue).isNotNull();

        treeMap.remove(new CategoryFixed(2, "c1"));
        assertThat(treeMap).hasSize(1);
    }

    static class CategoryFixed implements Comparable<CategoryFixed>{
        int id;
        String name;

        CategoryFixed(int id, String name) {
            this.id = id;
            this.name = name;
        }

        int getId() {
            return id;
        }

        String getName() {
            return name;
        }

        @Override
        public int compareTo(CategoryFixed o) {
            return Comparator.comparing(CategoryFixed::getName)
                .thenComparing(CategoryFixed::getId)
                .compare(this, o);
        }
    }

    static class BookFixed implements Comparable<BookFixed> {
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
        public int compareTo(BookFixed o) {
            return Comparator.comparing(BookFixed::getTitle)
                .thenComparing(BookFixed::getId)
                .compare(this, o);
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
