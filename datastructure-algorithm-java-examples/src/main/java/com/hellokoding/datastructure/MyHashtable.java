package com.hellokoding.datastructure;

import java.util.Arrays;
import java.util.Objects;

public class MyHashtable<K, V> {
    private static final int INITIAL_CAPACITY = 16;
    private static final float LOAD_FACTOR = 0.75f;
    private int size = 0;

    Entry<K,V>[] entries;

    @SuppressWarnings({"rawtypes", "unchecked"})
    public MyHashtable() {
        entries = (Entry<K, V>[]) new Entry[INITIAL_CAPACITY];
    }

    @SuppressWarnings({"rawtypes", "unchecked"})
    public MyHashtable(int capacity) {
        entries = (Entry<K, V>[]) new Entry[capacity];
    }

    public V put(K key, V value) {
        int index = hashFunction(key);
        Entry<K, V> headEntry = entries[index];
        Entry<K, V> currentEntry = headEntry;

        // Find key in the chain
        while (Objects.nonNull(currentEntry)) {
            if (Objects.equals(currentEntry.key, key)) {
                // Ignore duplicate key
                return currentEntry.value;
            }

            currentEntry = currentEntry.next;
        }

        // Add first to the chain
        Entry<K, V> newEntry = new Entry<>(key, value);
        newEntry.next = headEntry;
        entries[index] = newEntry;

        size++;

        resize();

        return null;
    }

    public V get(K key) {
        int index = hashFunction(key);
        Entry<K, V> currentEntry = entries[index];

        // Find key in the chain
        while (Objects.nonNull(currentEntry)) {
            if (Objects.equals(currentEntry.key, key)) {
                return currentEntry.value;
            }

            currentEntry = currentEntry.next;
        }

        return null;
    }

    public V remove(K key) {
        int index = hashFunction(key);
        Entry<K, V> currentEntry = entries[index];
        Entry<K, V> previousEntry = null;

        // Find key in the chain
        while (Objects.nonNull(currentEntry)) {
            if (Objects.equals(currentEntry.key, key)) {
                break;
            }

            previousEntry = currentEntry;
            currentEntry = currentEntry.next;
        }

        if (Objects.isNull(currentEntry)) {
            return null;
        }

        // Remove
        if (Objects.isNull(previousEntry)) {
            entries[index] = currentEntry.next;
        } else {
            previousEntry.next = currentEntry.next;
        }

        size--;

        return currentEntry.value;
    }

    public int size() {
        return size;
    }

    private void resize() {
        if (size <= LOAD_FACTOR * entries.length) return;

        Entry<K,V>[] currentEntries = entries;
        entries = (Entry<K, V>[]) new Entry[entries.length*2] ;

        rehash(currentEntries);
    }

    private void rehash(Entry<K,V>[] entries) {
        size = 0;

        for (Entry<K, V> entry : entries) {
            while (Objects.nonNull(entry)) {
                put(entry.key, entry.value);
                entry = entry.next;
            }
        }
    }

    private int hashFunction(K key) {
        int hashCode = key.hashCode();
        int index = hashCode % entries.length;

        return index;
    }

    public static class Entry<K, V> {
        K key;
        V value;

        Entry<K, V> next;

        Entry(K  key, V value) {
            this.key = key;
            this.value = value;
        }
    }

    public static void main(String[] args) {
        MyHashtable<String, Integer> myHashtable = new MyHashtable<>(2);
        myHashtable.put("k1", 1);
        myHashtable.put("k2", 2);
        myHashtable.put("k2", 2);
        myHashtable.put("k3", 3);

        Arrays.stream(myHashtable.entries)
            .forEach(e -> {
                if (Objects.nonNull(e)) {
                    System.out.printf("%s=%d%s", e.key, e.value, System.lineSeparator());
                }
            });

        System.out.println(myHashtable.size());

        System.out.println(myHashtable.get("k2"));

        System.out.println(myHashtable.remove("k1"));

        System.out.println(myHashtable.get("k1"));

        System.out.println(myHashtable.size());
    }
}
