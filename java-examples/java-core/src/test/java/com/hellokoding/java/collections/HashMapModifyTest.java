package com.hellokoding.java.collections;

import org.junit.Test;

import java.util.*;

import static org.assertj.core.api.Assertions.assertThat;

public class HashMapModifyTest {
    @Test
    public void put() {
        Map<String, Integer> map = new HashMap<>();
        map.put("k1", 1);
        map.put("k2", 2);
        assertThat(map.get("k2")).isEqualTo(2);

        map.put("k2", 20);
        assertThat(map.get("k2")).isEqualTo(20);
    }

    @Test
    public void putIfAbsent() {
        Map<String, Integer> map = new HashMap<>();
        map.put("k1", 1);
        map.put("k2", 2);
        map.put("k3", null);

        map.putIfAbsent("k4", 4);
        assertThat(map.get("k4")).isEqualTo(4);

        map.putIfAbsent("k2", 20);
        assertThat(map.get("k2")).isEqualTo(2);

        map.putIfAbsent("k3", 3);
        assertThat(map.get("k3")).isEqualTo(3);
    }

    @Test
    public void putAll() {
        Map<String, Integer> map = new HashMap<>();
        map.put("k1", 1);
        map.put("k2", null);

        Map<String, Integer> map2 = new HashMap<>();
        map2.put("k3", 3);
        map2.putAll(map);

        assertThat(map2.size()).isEqualTo(3);
    }

    @Test
    public void increaseValueWithCompute() {
        Map<String, Integer> map = new HashMap<>();
        map.put("k1", 1);
        map.put("k2", null);

        map.compute("k2", (k, v) -> v == null ? 1 : v++);
        assertThat(map.get("k2")).isEqualTo(1);
    }

    @Test
    public void replace() {
        Map<String, Integer> map = new HashMap<>();
        map.put("k1", 1);
        map.put("k2", null);

        map.replace("k2", 2);
        assertThat(map.get("k2")).isEqualTo(2);

        map.replace("k3", 3);
        assertThat(map.get("k3")).isNull();
    }

    @Test
    public void replaceIfAssociated() {
        Map<String, Integer> map = new HashMap<>();
        map.put("k1", 1);
        map.put("k2", null);

        map.replace("k1", 1, 10);
        assertThat(map.get("k1")).isEqualTo(10);

        map.replace("k2", 2, 20);
        assertThat(map.get("k2")).isNull();
    }

    @Test
    public void replaceAll() {
        Map<String, Integer> map = new HashMap<>();
        map.put("k1", 1);
        map.put("k2", null);

        map.replaceAll((k, v) -> 100);
        assertThat(map.get("k1")).isEqualTo(100);
        assertThat(map.get("k2")).isEqualTo(100);
    }

    @Test
    public void removeByKey() {
        Map<String, Integer> map = new HashMap<>();
        map.put("k1", 1);
        map.put("k2", null);

        Integer v = map.remove("k1");
        assertThat(v).isEqualTo(1);
        assertThat(map).hasSize(1);

        v = map.remove("k3");
        assertThat(v).isNull();
        assertThat(map).hasSize(1);
    }

    @Test
    public void removeByKeyValue() {
        Map<String, Integer> map = new HashMap<>();
        map.put("k1", 1);
        map.put("k2", null);

        boolean r = map.remove("k1", 10);
        assertThat(r).isFalse();
        assertThat(map).hasSize(2);

        r = map.remove("k2", null);
        assertThat(r).isTrue();
        assertThat(map).hasSize(1);

        r = map.remove("k3", 3);
        assertThat(r).isFalse();
        assertThat(map).hasSize(1);
    }

    @Test
    public void clear() {
        Map<String, Integer> map = new HashMap<>();
        map.put("k1", 1);
        map.put("k2", null);

        map.clear();
        assertThat(map).hasSize(0);
    }
}
