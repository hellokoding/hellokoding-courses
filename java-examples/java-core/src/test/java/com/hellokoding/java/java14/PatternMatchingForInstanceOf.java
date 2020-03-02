package com.hellokoding.java.java14;

import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class PatternMatchingForInstanceOf {
    public String instanceOf(Object o) {
        if (o instanceof Book) {
            Book b = (Book) o;
            if (b.id() == 1) {
                return b.title();
            }
        }

        return "";
    }

    public String instanceOfWithPatternMatching(Object o) {
        if (o instanceof Book b && b.id() == 1) {
            return b.title();
        } else {
            return "";
        }
    }

    @Test
    public void testInstanceOf() {
        Book b = new Book(1, "Java 14");
        String s = instanceOfWithPatternMatching(b);

        assertThat(s).isEqualTo("Java 14");
    }
}
