package com.hellokoding.java.collections;


import java.util.*;

public class HashMapExample {
    public static void main(String args[]) {
        // create and add key-value pairs
        Map<String, Integer> map = new HashMap<>();
        map.put("k1", 1);
        map.put("k2", 2);
        map.putIfAbsent("k2", 3);
        System.out.println(map); // {k1=1, k2=2}

        // iterate
        map.entrySet().forEach(System.out::println);
        map.keySet().forEach(System.out::println);
        map.values().forEach(System.out::println);

        // sort
        SortedMap<String, Integer> sortedMap = new TreeMap<>(map);
        System.out.println(sortedMap); // {k1=1, k2=2}

        // query
        System.out.println(map.get("k1")); // 1
        System.out.println(map.containsKey("k1")); // true
        System.out.println(map.containsValue(1)); // true
        System.out.println(map.size()); // 2

        // update value
        map.replace("k2", 20);
        System.out.println(map); // {k1=1, k2=2}

        // remove entries
        map.remove("k2");
        System.out.println(map); // {k1=1}
        map.clear();
        System.out.println(map); // {}
    }
}