package com.hellokoding.java.collections;

import org.junit.Test;

import java.util.*;

import static org.assertj.core.api.Assertions.assertThat;

public class ComparableTest {
    @Test(expected = ClassCastException.class)
    public void whenArraysSortNonComparable_thenThrowException(){
        NonComparableBook[] books = new NonComparableBook[]{
            new NonComparableBook("C"),
            new NonComparableBook("A"),
            new NonComparableBook("D")
        };

        Arrays.sort(books);
    }

    @Test
    public void whenArraysSortComparable_thenSuccess(){
        ComparableBook[] books = new ComparableBook[]{
            new ComparableBook("C"),
            new ComparableBook("A"),
            new ComparableBook("D")
        };

        Arrays.sort(books);

        assertThat(books[0].title).isEqualTo("A");
        assertThat(books[1].title).isEqualTo("C");
        assertThat(books[2].title).isEqualTo("D");
    }

    @Test
    public void whenCollectionSortComparable_thenSuccess(){
        List<ComparableBook> books = Arrays.asList(
            new ComparableBook("C"),
            new ComparableBook("A"),
            new ComparableBook("D")
        );

        Collections.sort(books);

        assertThat(books.get(0).title).isEqualTo("A");
        assertThat(books.get(1).title).isEqualTo("C");
        assertThat(books.get(2).title).isEqualTo("D");
    }
}
