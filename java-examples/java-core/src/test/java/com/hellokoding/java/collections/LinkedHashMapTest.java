package com.hellokoding.java.collections;

import org.junit.Test;

import java.util.*;

import static java.util.Map.Entry.comparingByKey;
import static java.util.Map.Entry.comparingByValue;
import static org.assertj.core.api.Assertions.assertThat;

public class LinkedHashMapTest {
    @Test
    public void declare() {
        Map<String, Integer> linkedHashMap1 = new LinkedHashMap<>();
        assertThat(linkedHashMap1).isInstanceOf(LinkedHashMap.class);

        LinkedHashMap<String, Integer> linkedHashMap2 = new LinkedHashMap<>();
    }

    @Test
    public void initInOneLineWithFactoryMethods() {
        // create and initialize a LinkedHashMap from Java 9+ Map.of
        Map<String, Integer> linkedHashMap1 = new LinkedHashMap<>((Map.of("k1", 1, "k3", 2, "k2", 3)));
        assertThat(linkedHashMap1).hasSize(3);

        // create and initialize a LinkedHashMap from Java 9+ Map.ofEntries
        Map<String, Integer> linkedHashMap2 = new LinkedHashMap<>(Map.ofEntries(Map.entry("k4", 4), Map.entry("k5", 5)));
        assertThat(linkedHashMap2).hasSize(2);
    }

    @Test
    public void initializeWithPutIfAbsent() {
        // Create a new LinkedHashMap
        Map<String, Integer> linkedHashMap = new LinkedHashMap<>();

        // Add elements to LinkedHashMap
        linkedHashMap.putIfAbsent("k1", 1);
        linkedHashMap.putIfAbsent("k2", 2);
        linkedHashMap.putIfAbsent("k3", 3);

        // Can add null key and value
        linkedHashMap.putIfAbsent(null, 4);
        linkedHashMap.putIfAbsent("k4", null);

        // Duplicate key will be ignored
        linkedHashMap.putIfAbsent("k1", 10);
        assertThat(linkedHashMap).hasSize(5);

        // The output ordering is predictable as LinkedHashMap is reserved the insertion order
        System.out.println(linkedHashMap);
    }

    @Test
    public void iterateOverKeyValuePairs() {
        Map<String, Integer> linkedHashMap = new LinkedHashMap<>(Map.of("k1", 1, "k2", 2));
        linkedHashMap.forEach((k, v) -> System.out.printf("%s=%d ", k, v));
    }

    @Test
    public void iterateOverEntrySetKeySetAndValues() {
        Map<String, Integer> linkedHashMap = new LinkedHashMap<>(Map.of("k1", 1, "k2", 2));
        linkedHashMap.entrySet().forEach(e -> System.out.printf("%s ", e));
        linkedHashMap.keySet().forEach(k -> System.out.printf("%s ", k));
        linkedHashMap.values().forEach(v -> System.out.printf("%s ", v));
    }

    @Test
    public void retrieve() {
        Map<String, Integer> linkedHashMap = new LinkedHashMap<>(Map.of("k1", 1, "k2", 2));

        Set<Map.Entry<String, Integer>> entrySet = linkedHashMap.entrySet();
        assertThat(entrySet).contains(Map.entry("k1", 1), Map.entry("k2", 2));

        Set<String> keySet = linkedHashMap.keySet();
        assertThat(keySet).contains("k1", "k2");

        Collection<Integer> values = linkedHashMap.values();
        assertThat(values).contains(1, 2);
    }

    @Test
    public void getValueByKey() {
        Map<String, Integer> linkedHashMap = new LinkedHashMap<>(Map.of("k1", 1, "k2", 2));
        int value = linkedHashMap.get("k1");

        assertThat(value).isEqualTo(1);
    }

    @Test
    public void filter() {
        Map<String, Integer> linkedHashMap = new LinkedHashMap<>(Map.of("k1", 1, "k2", 2));
        Integer[] arr = linkedHashMap.values().stream().filter(v -> v > 1).toArray(Integer[]::new);
        assertThat(arr).contains(2);
    }

    @Test
    public void containsPutReplaceRemove() {
        Map<String, Integer> linkedHashMap = new LinkedHashMap<>(Map.of("k1", 1, "k2", 2));

        boolean containedKey = linkedHashMap.containsKey("k1");
        assertThat(containedKey).isTrue();

        boolean containedValue = linkedHashMap.containsValue(2);
        assertThat(containedValue).isTrue();

        linkedHashMap.put("k3", 3);
        assertThat(linkedHashMap).hasSize(3);

        linkedHashMap.replace("k1", 10);
        assertThat(linkedHashMap).contains(Map.entry("k1", 10), Map.entry("k2", 2), Map.entry("k3", 3));

        linkedHashMap.remove("k3");
        assertThat(linkedHashMap).contains(Map.entry("k1", 10), Map.entry("k2", 2));
    }

    @Test
    public void objectsComparingProblem(){
        Map<Category, Book> linkedHashMap = new LinkedHashMap<>();

        linkedHashMap.put(new Category(1, "c1"), new Book(1, "b1"));

        boolean containedKey = linkedHashMap.containsKey(new Category(1, "c1"));
        assertThat(containedKey).isFalse();

        boolean containedValue = linkedHashMap.containsValue(new Book(1, "b1"));
        assertThat(containedValue).isFalse();

        linkedHashMap.put(new Category(1, "c1"), new Book(1, "b1"));
        assertThat(linkedHashMap).hasSize(2);

        Book previousValue = linkedHashMap.replace(new Category(1, "c1"), new Book(2, "b1"));
        assertThat(previousValue).isNull();

        linkedHashMap.remove(new Category(1, "c1"));
        assertThat(linkedHashMap).hasSize(2);
    }

    static class Category {
        int id;
        String name;

        Category(int id, String name) {
            this.id = id;
            this.name = name;
        }
    }

    static class Book {
        int id;
        String title;

        Book(int id, String title) {
            this.id = id;
            this.title = title;
        }
    }

    @Test
    public void objectsComparingFixed(){
        Map<CategoryFixed, BookFixed> linkedHashMap = new LinkedHashMap<>();

        linkedHashMap.put(new CategoryFixed(1, "c1"), new BookFixed(1, "b1"));

        boolean containedKey = linkedHashMap.containsKey(new CategoryFixed(1, "c1"));
        assertThat(containedKey).isTrue();

        boolean containedValue = linkedHashMap.containsValue(new BookFixed(1, "b1"));
        assertThat(containedValue).isTrue();

        linkedHashMap.put(new CategoryFixed(1, "c1"), new BookFixed(1, "b1"));
        assertThat(linkedHashMap).hasSize(1);

        BookFixed previousValue = linkedHashMap.replace(new CategoryFixed(1, "c1"), new BookFixed(2, "b1"));
        assertThat(previousValue).isNotNull();

        linkedHashMap.remove(new CategoryFixed(1, "c1"));
        assertThat(linkedHashMap).hasSize(0);
    }

    static class CategoryFixed {
        int id;
        String name;

        CategoryFixed(int id, String name) {
            this.id = id;
            this.name = name;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            CategoryFixed that = (CategoryFixed) o;
            return id == that.id &&
                    Objects.equals(name, that.name);
        }

        @Override
        public int hashCode() {
            return Objects.hash(id, name);
        }
    }

    static class BookFixed {
        int id;
        String title;

        BookFixed(int id, String title) {
            this.id = id;
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

    @Test
    public void sortByKeysAndByValues_WithArrayListAndComparator() {
        Map.Entry<String, Integer> e1 = Map.entry("k1", 1);
        Map.Entry<String, Integer> e2 = Map.entry("k2", 20);
        Map.Entry<String, Integer> e3 = Map.entry("k3", 3);

        Map<String, Integer> linkedHashMap = new LinkedHashMap<>(Map.ofEntries(e3, e1, e2));

        List<Map.Entry<String, Integer>> arrayList1 = new ArrayList<>(linkedHashMap.entrySet());
        arrayList1.sort(comparingByKey(Comparator.naturalOrder()));
        assertThat(arrayList1).containsExactly(e1, e2, e3);

        List<Map.Entry<String, Integer>> arrayList2 = new ArrayList<>(linkedHashMap.entrySet());
        arrayList2.sort(comparingByValue(Comparator.reverseOrder()));
        assertThat(arrayList2).containsExactly(e2, e3, e1);
    }
}
