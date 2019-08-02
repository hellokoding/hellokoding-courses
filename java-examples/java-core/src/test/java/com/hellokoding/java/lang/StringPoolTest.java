package com.hellokoding.java.lang;

import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class StringPoolTest {
    @Test
    public void testStringLiterals() {
        String s1 = "Hello Koding"; // This string is created and stored in the String Pool

        String s2 = "Hello Koding"; // This string is reused from s1

        assertThat(s1).isSameAs(s2);
    }

    @Test
    public void testStringObjects() {
        String s1 = new String("Hello Koding"); // This string is created and stored in a random location in heap memory

        String s2 = new String("Hello Koding"); // This string is created and stored in a random location in heap memory

        assertThat(s1).isNotSameAs(s2);
    }

    @Test
    public void testStringLiteralVsObject() {
        String s1 = new String("Hello Koding"); // This string is created and stored in heap memory

        String s2 = "Hello Koding"; // This string is created and stored in String Pool

        assertThat(s1).isNotSameAs(s2);
    }

    @Test
    public void testStringIntern() {
        String s1 = new String("Hello Koding");
        s1 = s1.intern();

        String s2 = "Hello Koding";

        assertThat(s1).isSameAs(s2);
    }
}
