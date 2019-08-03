package com.hellokoding.java.lang;

import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class StringPoolTest {
    @Test
    public void testStringLiterals() {
        // This string is created and stored in the string pool
        String s1 = "Hello Koding";

        // This string is reused from the string pool
        String s2 = "Hello Koding";

        assertThat(s1).isSameAs(s2);
    }

    @Test
    public void testStringObjects() {
        // This string is created and stored in heap memory
        String s1 = new String("Hello Koding");

        // This string is created and stored in heap memory
        String s2 = new String("Hello Koding");

        assertThat(s1).isNotSameAs(s2);
    }

    @Test
    public void testStringLiteralVsObject() {
        // This string is created and stored in heap memory
        String s1 = new String("Hello Koding");

        // This string is created and stored in the string pool
        String s2 = "Hello Koding";

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
