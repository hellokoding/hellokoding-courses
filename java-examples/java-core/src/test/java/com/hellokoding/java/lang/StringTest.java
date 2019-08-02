package com.hellokoding.java.lang;

import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class StringTest {
    @Test
    public void stringIsImmutable() {
        String s1 = "Hello Koding";
        String s2 = s1;
        assertThat(s1 == s2).isTrue();

        s1 = s1.concat(" is awesome!");
        assertThat(s1 == s2).isFalse();
    }

    @Test
    public void StringBuilderIsMutable() {
        StringBuilder s1 = new StringBuilder("Hello Koding");
        StringBuilder s2 = s1;
        assertThat(s1 == s2).isTrue();

        s1 = s1.append(" is awesome!");
        assertThat(s1 == s2).isTrue();
    }

    @Test
    public void StringBufferIsMutable() {
        StringBuffer s1 = new StringBuffer("Hello Koding");
        StringBuffer s2 = s1;
        assertThat(s1 == s2).isTrue();

        s1 = s1.append(" is awesome!");
        assertThat(s1 == s2).isTrue();
    }
}
