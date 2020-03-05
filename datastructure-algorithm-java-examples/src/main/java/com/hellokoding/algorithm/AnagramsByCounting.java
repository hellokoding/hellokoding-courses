package com.hellokoding.algorithm;

public class AnagramsByCounting {
    static boolean areAnagrams(String s, String t) {
        if (s.length() != t.length()) {
            return false;
        }

        for (char ch = 'a'; ch <= 'z'; ch++) {
            if (countOccurrences(ch, s) != countOccurrences(ch, t)) {
                return false;
            }
        }

        return true;
    }

    static int countOccurrences(char c, String s) {
        return s.length() - s.replace(String.valueOf(c), "").length();
    }

    public static void main(String[] args) {
        System.out.println(areAnagrams("listen", "silent"));
        System.out.println(areAnagrams("eleven plus two", "twelve plus one"));
        System.out.println(areAnagrams("rat", "car"));
    }
}
