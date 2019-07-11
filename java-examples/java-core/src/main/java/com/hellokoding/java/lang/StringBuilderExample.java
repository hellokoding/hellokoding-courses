package com.hellokoding.java.lang;


public class StringBuilderExample {
    public static void main(String args[]) {
        System.out.println(concatByStringBuilder());
    }

    static String concatByStringBuilder() {
        StringBuilder sb = new StringBuilder();

        sb.append(1);
        sb.append("a");
        sb.insert(0, 1.5);

        return sb.toString();
    }
}