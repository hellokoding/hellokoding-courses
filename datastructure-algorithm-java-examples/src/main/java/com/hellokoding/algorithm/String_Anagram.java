package com.hellokoding.algorithm;

import java.util.Arrays;

public class String_Anagram {
    static boolean isAnagram(String str1, String str2) {
        char[] charArr1 = str1.toCharArray();
        char[] charArr2 = str2.toCharArray();

        Arrays.sort(charArr1);
        Arrays.sort(charArr2);

        return Arrays.equals(charArr1, charArr2);
    }

    public static void main(String[] args) {
        System.out.println(isAnagram("abc", "bca"));
        System.out.println(isAnagram("abc", "bcb"));
    }
}
