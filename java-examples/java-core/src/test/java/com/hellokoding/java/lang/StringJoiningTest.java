package com.hellokoding.java.lang;

import org.junit.Test;

import java.util.*;

import static org.assertj.core.api.Assertions.assertThat;

public class StringJoiningTest {
    @Test
    public void joinStringsWithStringJoiners() {
        StringJoiner stringJoiner = new StringJoiner(", ");

        stringJoiner.add("apple")
            .add("orange")
            .add("passion");

        String result = stringJoiner.toString();

        assertThat(result).isEqualTo("apple, orange, passion");
    }

    @Test
    public void joinListOfStringsWithStringJoin() {
        List<String> lst = Arrays.asList("apple", "orange", "passion");

        String result = String.join(", ", lst);

        assertThat(result).isEqualTo("apple, orange, passion");
    }

    @Test
    public void joinArrayOfStringsWithStringJoin() {
        String[] arr = {"apple", "orange", "passion"};

        String result = String.join(", ", arr);

        assertThat(result).isEqualTo("apple, orange, passion");
    }

    @Test
    public void joinStringsWithStringBuilder() {
        StringBuilder stringBuilder = new StringBuilder();

        stringBuilder.append("apple")
            .append(", ")
            .append("orange")
            .append(", ")
            .append("passion");

        String result = stringBuilder.toString();

        assertThat(result).isEqualTo("apple, orange, passion");
    }
}
