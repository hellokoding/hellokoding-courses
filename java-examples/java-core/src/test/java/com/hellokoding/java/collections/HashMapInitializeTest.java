package com.hellokoding.java.collections;

import org.junit.Test;

import java.util.*;

import static org.assertj.core.api.Assertions.assertThat;

public class HashMapInitializeTest {
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
    public void initInOneLineFromAnExistingMap() {
        Map<String, Integer> map1 = new HashMap<>(Map.ofEntries(Map.entry("k4", 4), Map.entry("k5", 5)));

        // create and initialize from an existing map
        Map<String, Integer> map2 = new HashMap<>(map1);
        assertThat(map2).hasSize(2);
    }

    @Test
    public void initializeWithPut() {
        // Create a new HashMap
        Map<String, Integer> map = new HashMap<>();

        // Add elements to HashMap
        map.put("k1", 1);
        map.put("k2", 2);
        map.put("k3", 3);

        // Can add null key and value
        map.put(null, 4);
        map.put("k4", null);

        // Value of duplicate key will be overridden
        map.put("k1", 10);

        assertThat(map).hasSize(5);

        // The output ordering will be vary as HashMap is not reserved the insertion order
        System.out.println(map);
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
    public void initializeWithDoubleBrace() {
        // Create a new HashMap
        Map<String, Integer> map = new HashMap<>(){{
            put("k1", 1);
            put("k3", 2);
            put("k2", 3);
        }};

        assertThat(map).hasSize(3);
    }

    @Test
    public void initializeWithPutAll() {
        // Create a new HashMap
        Map<String, Integer> map1 = new HashMap<>();

        // Add all from Java 9+ Map.of
        map1.putAll(Map.of("k1", 1, "k3", 2, "k2", 3));

        // Add all from Java 9+ Map.ofEntries
        map1.putAll(Map.ofEntries(Map.entry("k4", 4), Map.entry("k5", 5)));

        assertThat(map1).hasSize(5);

        // Add all from an existing map
        Map<String, Integer> map2 = new HashMap<>();
        map2.putAll(Map.copyOf(map1));
        assertThat(map2).hasSize(5);
    }


    @Test
    public void initializeWithInitialCapacityAndLoadFactor() {
        // init with 16 and 0.75 as default initial capacity and default load factor
        Map<String, Integer> map1 = new HashMap<>();

        int initialCapacity = 2;
        // init with 2 and 0.75 as initial capacity and default load factor
        Map<String, Integer> map2 = new HashMap<>(initialCapacity);

        float loadFactor = 1;
        // init with 16 and 1 as default initial capacity and load factor
        Map<String, Integer> map3 = new HashMap<>(initialCapacity, loadFactor);
    }
}
