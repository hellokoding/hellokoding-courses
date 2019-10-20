package com.hellokoding.java.collections;

import org.junit.Test;

import java.util.*;

import static java.util.Map.Entry.comparingByKey;
import static java.util.Map.Entry.comparingByValue;
import static org.assertj.core.api.Assertions.assertThat;

public class HashMapTest {
    @Test
    public void declare() {
        Map<String, Integer> map1 = new HashMap<>();
        assertThat(map1).isInstanceOf(HashMap.class);

        HashMap<String, Integer> map2 = new HashMap<>();
    }

    @Test
    public void initInOneLineWithFactoryMethods() {
        // create and initialize a HashMap from Java 9+ Map.of
        Map<String, Integer> map1 = new HashMap<>((Map.of("k1", 1, "k3", 2, "k2", 3)));
        assertThat(map1).hasSize(3);

        // create and initialize a HashMap from Java 9+ Map.ofEntries
        Map<String, Integer> map2 = new HashMap<>(Map.ofEntries(Map.entry("k4", 4), Map.entry("k5", 5)));
        assertThat(map2).hasSize(2);
    }

    @Test
    public void initializeWithPutIfAbsent() {
        // Create a new HashMap
        Map<String, Integer> map = new HashMap<>();

        // Add elements to HashMap
        map.putIfAbsent("k1", 1);
        map.putIfAbsent("k2", 2);
        map.putIfAbsent("k3", 3);

        // Can add null key and value
        map.putIfAbsent(null, 4);
        map.putIfAbsent("k4", null);

        // Duplicate key will be ignored
        map.putIfAbsent("k1", 10);
        assertThat(map).hasSize(5);

        // The output ordering will be vary as HashMap is not reserved the insertion order
        System.out.println(map);
    }

    @Test
    public void iterateOverKeyValuePairs() {
        Map<String, Integer> map = new HashMap<>(Map.of("k1", 1, "k2", 2));
        map.forEach((k, v) -> System.out.printf("%s=%d ", k, v));
    }

    @Test
    public void iterateOverKeySet() {
        Map<String, Integer> map = new HashMap<>(Map.of("k1", 1, "k2", 2));
        map.values().forEach(k -> System.out.printf("%s ", k));
    }

    @Test
    public void retrieve() {
        Map<String, Integer> hashMap = new HashMap<>(Map.of("k1", 1, "k2", 2));

        Set<Map.Entry<String, Integer>> entrySet = hashMap.entrySet();
        assertThat(entrySet).contains(Map.entry("k1", 1), Map.entry("k2", 2));

        Set<String> keySet = hashMap.keySet();
        assertThat(keySet).contains("k1", "k2");

        Collection<Integer> values = hashMap.values();
        assertThat(values).contains(1, 2);
    }

    @Test
    public void getValueByKey() {
        Map<String, Integer> map = new HashMap<>(Map.of("k1", 1, "k2", 2));
        int value = map.get("k1");

        assertThat(value).isEqualTo(1);
    }

    @Test
    public void filter() {
        Map<String, Integer> map = new HashMap<>(Map.of("k1", 1, "k2", 2));
        Integer[] arr = map.values().stream().filter(v -> v > 1).toArray(Integer[]::new);
        assertThat(arr).contains(2);
    }

    @Test
    public void containsPutReplaceRemove() {
        Map<String, Integer> map = new HashMap<>(Map.of("k1", 1, "k2", 2));

        boolean containedKey = map.containsKey("k1");
        assertThat(containedKey).isTrue();

        boolean containedValue = map.containsValue(2);
        assertThat(containedValue).isTrue();

        map.put("k3", 3);
        assertThat(map).hasSize(3);

        map.replace("k1", 10);
        assertThat(map).contains(Map.entry("k1", 10), Map.entry("k2", 2), Map.entry("k3", 3));

        map.remove("k3");
        assertThat(map).contains(Map.entry("k1", 10), Map.entry("k2", 2));
    }

    @Test
    public void objectsComparingProblem(){
        Map<Category, Book> hashMap = new HashMap<>();

        hashMap.put(new Category(1, "c1"), new Book(1, "b1"));

        boolean containedKey = hashMap.containsKey(new Category(1, "c1"));
        assertThat(containedKey).isFalse();

        boolean containedValue = hashMap.containsValue(new Book(1, "b1"));
        assertThat(containedValue).isFalse();

        hashMap.put(new Category(1, "c1"), new Book(1, "b1"));
        assertThat(hashMap).hasSize(2);

        Book previousValue = hashMap.replace(new Category(1, "c1"), new Book(2, "b1"));
        assertThat(previousValue).isNull();

        hashMap.remove(new Category(1, "c1"));
        assertThat(hashMap).hasSize(2);
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
        Map<CategoryFixed, BookFixed> hashMap = new HashMap<>();

        hashMap.put(new CategoryFixed(1, "c1"), new BookFixed(1, "b1"));

        boolean containedKey = hashMap.containsKey(new CategoryFixed(1, "c1"));
        assertThat(containedKey).isTrue();

        boolean containedValue = hashMap.containsValue(new BookFixed(1, "b1"));
        assertThat(containedValue).isTrue();

        hashMap.put(new CategoryFixed(1, "c1"), new BookFixed(1, "b1"));
        assertThat(hashMap).hasSize(1);

        BookFixed previousValue = hashMap.replace(new CategoryFixed(1, "c1"), new BookFixed(2, "b1"));
        assertThat(previousValue).isNotNull();

        hashMap.remove(new CategoryFixed(1, "c1"));
        assertThat(hashMap).hasSize(0);
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

        Map<String, Integer> map = new HashMap<>(Map.ofEntries(e3, e1, e2));

        List<Map.Entry<String, Integer>> arrayList1 = new ArrayList<>(map.entrySet());
        arrayList1.sort(comparingByKey(Comparator.naturalOrder()));
        assertThat(arrayList1).containsExactly(e1, e2, e3);

        List<Map.Entry<String, Integer>> arrayList2 = new ArrayList<>(map.entrySet());
        arrayList2.sort(comparingByValue(Comparator.reverseOrder()));
        assertThat(arrayList2).containsExactly(e2, e3, e1);
    }
}
