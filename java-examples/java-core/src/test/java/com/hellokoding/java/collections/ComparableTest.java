package com.hellokoding.java.collections;

import org.junit.Test;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

public class ComparableTest {
    @Test(expected = ClassCastException.class)
    public void givenNonComparable_whenArraysSort_thenThrowException(){
        // given
        NonComparableBook[] books = new NonComparableBook[]{
            new NonComparableBook("C"),
            new NonComparableBook("A"),
            new NonComparableBook("D")
        };

        // when
        Arrays.sort(books);
    }

    @Test
    public void givenComparable_whenArraysSort_thenSuccess(){
        // given
        ComparableBook[] books = new ComparableBook[]{
            new ComparableBook("C"),
            new ComparableBook("A"),
            new ComparableBook("D")
        };

        // when
        Arrays.sort(books);

        // then
        String actual = Stream.of(books).map(e -> e.title).collect(Collectors.joining());
        assertThat(actual).isEqualTo("ACD");
    }

    @Test
    public void givenComparable_whenCollectionSort_thenSuccess(){
        // given
        List<ComparableBook> books = Arrays.asList(
            new ComparableBook("C"),
            new ComparableBook("A"),
            new ComparableBook("D")
        );

        // when
        Collections.sort(books);

        // then
        String actual = books.stream().map(e -> e.title).collect(Collectors.joining());
        assertThat(actual).isEqualTo("ACD");
    }
}
