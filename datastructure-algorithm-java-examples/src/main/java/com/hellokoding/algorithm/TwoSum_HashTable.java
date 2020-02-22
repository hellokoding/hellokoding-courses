package com.hellokoding.algorithm;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;

public class TwoSum_HashTable {
    static boolean hashTable(int[] a, int k) {
        HashSet<Integer> hashTable = new HashSet<>();
        for (int i = 0; i < a.length ; i++) {
            if (hashTable.contains(k - a[i])) return true;
            hashTable.add(a[i]);
        }

        return false;
    }

    public static void main(String[] args) {
        int[] a = {1, 3, 7};
        System.out.println(hashTable(a, 8));
        System.out.println(hashTable(a, 5));

        int[] b = {4, -9, 0, 11, 6, -20, 1, 7};
        System.out.println(hashTable(b, -14));
        System.out.println(hashTable(b, -15));
    }
}
