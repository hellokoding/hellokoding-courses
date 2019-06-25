package com.hellokoding.java.lang;

import org.junit.Test;

import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

public class EqualsAndHashCodeTest {
    @Test
    public void givenNonEqualsImplement_whenCompareObjects_thenReturnsFalse() {
        BookWithoutEquals book1 = new BookWithoutEquals("A");
        BookWithoutEquals book2 = new BookWithoutEquals("A");

        assertThat(book1.equals(book2)).isFalse();
    }

    @Test
    public void givenEqualsImplement_whenCompareObjects_thenReturnsTrue() {
        BookWithEquals book1 = new BookWithEquals("A", 1);
        BookWithEquals book2 = new BookWithEquals("A", 1);

        assertThat(book1.equals(book2)).isTrue();
    }

    @Test
    public void givenEqualsImplement_whenCompareInHashMap_thenReturnsFalse() {
        BookWithEquals bookA = new BookWithEquals("A", 1);
        BookWithEquals bookB = new BookWithEquals("B", 2);
        Map<BookWithEquals, Integer> bookMap = Map.of(bookA, 1, bookB, 2);

        assertThat(bookMap.containsKey(new BookWithEquals("A", 1))).isFalse();
    }

    @Test
    public void givenEqualsAndHashCodeImplement_whenCompareInHashMap_thenReturnsTrue() {
        BookWithEqualsAndHashCode bookA = new BookWithEqualsAndHashCode("A", 1);
        BookWithEqualsAndHashCode bookB = new BookWithEqualsAndHashCode("B", 2);
        Map<BookWithEqualsAndHashCode, Integer> bookMap = Map.of(bookA, 1, bookB, 2);

        assertThat(bookMap.containsKey(new BookWithEqualsAndHashCode("A", 1))).isTrue();
    }
}
