package com.hellokoding.java.collections;

import org.junit.Test;

import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

public class ComparatorTest {
    @Test
    public void givenNonComparableWithComparator_whenArraysSort_thenSuccess(){
        // given
        NonComparableBook[] books = new NonComparableBook[]{
            new NonComparableBook("C"),
            new NonComparableBook("A"),
            new NonComparableBook("D")
        };

        // when
        Arrays.sort(books, Comparator.comparing(e -> e.title));

        // then
        String actual = Stream.of(books).map(e -> e.title).collect(Collectors.joining());
        assertThat(actual).isEqualTo("ACD");
    }

    @Test
    public void givenComparableWithComparator_whenArraysSort_thenSuccess(){
        // given
        ComparableBook[] books = new ComparableBook[]{
            new ComparableBook("C"),
            new ComparableBook("A"),
            new ComparableBook("D")
        };

        // when
        Arrays.sort(books, Comparator.reverseOrder());

        String actual = Stream.of(books).map(e -> e.title).collect(Collectors.joining());
        assertThat(actual).isEqualTo("DCA");
    }

    @Test
    public void givenComparableWithComparator_whenCollectionSort_thenSuccess(){
        // given
        List<ComparableBook> books = Arrays.asList(
            new ComparableBook("C"),
            new ComparableBook("A"),
            new ComparableBook("D")
        );

        // when
        Collections.sort(books, Comparator.comparing(e -> e.title));

        // when
        String actual = books.stream().map(e -> e.title).collect(Collectors.joining());
        assertThat(actual).isEqualTo("ACD");
    }
}
