package com.hellokoding.java.collections;

import org.junit.Test;

import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class ComparatorTest {
    @Test
    public void whenArraysSortNonComparableWithComparator_thenSuccess(){
        NonComparableBook[] books = new NonComparableBook[]{
            new NonComparableBook("C"),
            new NonComparableBook("A"),
            new NonComparableBook("D")
        };

        Arrays.sort(books, Comparator.comparing(e -> e.title));
    }

    @Test
    public void whenArraysSortComparableWithComparator_thenSuccess(){
        ComparableBook[] books = new ComparableBook[]{
            new ComparableBook("C"),
            new ComparableBook("A"),
            new ComparableBook("D")
        };

        Arrays.sort(books, Comparator.reverseOrder());

        assertThat(books[0].title).isEqualTo("D");
        assertThat(books[1].title).isEqualTo("C");
        assertThat(books[2].title).isEqualTo("A");
    }

    @Test
    public void whenCollectionSortComparableWithComparator_thenSuccess(){
        List<ComparableBook> books = Arrays.asList(
            new ComparableBook("C"),
            new ComparableBook("A"),
            new ComparableBook("D")
        );

        Collections.sort(books, Comparator.reverseOrder());

        assertThat(books.get(0).title).isEqualTo("D");
        assertThat(books.get(1).title).isEqualTo("C");
        assertThat(books.get(2).title).isEqualTo("A");
    }
}
