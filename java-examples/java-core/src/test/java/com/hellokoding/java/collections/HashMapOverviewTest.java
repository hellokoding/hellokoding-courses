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
    public void initializeWithPutIfAbsent() {
        // Create a new HashMap
        Map<String, Integer> map = new HashMap<>();

        // Add elements to HashMap
        map.putIfAbsent("k1", 1);

        // Can add null key and value
        map.putIfAbsent(null, 2);
        map.putIfAbsent("k3", null);

        // Duplicate key will be ignored
        map.putIfAbsent("k1", 10);

        assertThat(map).hasSize(3);
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
