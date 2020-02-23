package com.hellokoding.algorithm;

import java.util.HashSet;
import java.util.Set;

public class LongestDistinctChars_SlidingWindow {
    static String slidingWindow(String s) {
        int n = s.length();
        int l = 0, i = 0, j = 0;
        Set<Character> chars = new HashSet<>();

        while(i < n && j < n) {
            if(!chars.contains(s.charAt(j))) {
                chars.add(s.charAt(j++));
                l = Math.max(l, j-i);
            } else {
                chars.remove(s.charAt(i++));
            }
        }

        return s.substring(i, i+l);
    }

    public static void main(String[] args) {
        System.out.println(slidingWindow("HelloKoding"));
    }
}
