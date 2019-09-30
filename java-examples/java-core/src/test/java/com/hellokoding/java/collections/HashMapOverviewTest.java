package com.hellokoding.java.collections;

import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

public class HashMapOverviewTest {
    @Test
    public void declare() {
        Map<String, Integer> map1 = new HashMap<>();
        assertThat(map1).isInstanceOf(HashMap.class);

        HashMap<String, Integer> map2 = new HashMap<>();
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

        // The output ordering will be vary as Map is not reserved the insertion order
        System.out.println(map);
    }

    @Test
    public void iterate() {
        Map<String, Integer> map = new HashMap<>(Map.of("k1", 1, "k2", 2));
        map.forEach((k, v) -> System.out.printf("%s=%d%s", k, v, System.lineSeparator()));
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
