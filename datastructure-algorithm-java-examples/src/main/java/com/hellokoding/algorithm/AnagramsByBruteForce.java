package com.hellokoding.algorithm;

public class AnagramsByBruteForce {
    static boolean areAnagrams(String s, String t) {
        if (s.length() != t.length()) {
            return false;
        }

        return areAnagramsByBackTracking(0, s, t);
    }

    static boolean areAnagramsByBackTracking(int i, String s1, String s2) {
        if (i == s1.length()-1) {
            return s1.equals(s2);
        }

        for(int j=i; j<s1.length(); j++) {
            s1 = swap(s1, i, j);

            if (areAnagramsByBackTracking(i+1, s1, s2)) {
                return true;
            }

            s1 = swap(s1, i, j); // backtracking
        }

        return false;
    }

    static String swap(String str, int i, int j) {
        char[] arr = str.toCharArray();

        char temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;

        return String.valueOf(arr);
    }

    public static void main(String[] args) {
        System.out.println(areAnagrams("listen", "silent"));
        System.out.println(areAnagrams("rat", "car"));
    }
}
