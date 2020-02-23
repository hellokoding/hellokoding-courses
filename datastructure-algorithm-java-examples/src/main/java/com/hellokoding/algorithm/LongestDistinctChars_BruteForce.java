package com.hellokoding.algorithm;

import java.util.HashSet;
import java.util.Set;

public class LongestDistinctChars_BruteForce {
    static String bruteforce(String s) {
        int l = 0, i = 0, j = 0;
        int n = s.length();

        for (; i < n; i++) {
            for (j = i+1; j <= n; j++) {
                if (isUnique(s, i, j)) l = Math.max(l, j-i);
                else break;
            }

            if (j == n+1) break;
        }

        return s.substring(i, i+l);
    }

    static boolean isUnique(String s, int i, int j) {
        HashSet<Character> set = new HashSet<>();
        for (int k = i; k < j; k++) {
            if (set.contains(s.charAt(k))) return false;
            set.add(s.charAt(k));
        }

        return true;
    }

    public static void main(String[] args) {
        System.out.println(bruteforce("HelloKoding"));
    }
}
