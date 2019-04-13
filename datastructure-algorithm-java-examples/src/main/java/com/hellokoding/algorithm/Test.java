package com.hellokoding.algorithm;

public class Test {
    static long repeatedString(String s, long n) {
        long countAInS = 0L;
        for(char c : s.toCharArray()){
            if (c == 'a') countAInS++;
        }

        long sLength = s.length();
        countAInS = countAInS * (n/sLength);

        for(char c : s.substring(0, (int)(n % sLength)).toCharArray()) {
            if (c == 'a') countAInS++;
        }

        return countAInS;
    }
}
