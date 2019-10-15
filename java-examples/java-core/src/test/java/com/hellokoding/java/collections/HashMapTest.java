package com.hellokoding.java.collections;

import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

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
    public void replace() {
        Map<String, Integer> map = new HashMap<>();
        map.put("k1", 1);
        map.put("k2", 2);

        map.replace("k2", 20);
        map.replace("k3", 3);

        assertThat(map).contains(Map.entry("k1", 1), Map.entry("k2", 20));
    }
}
