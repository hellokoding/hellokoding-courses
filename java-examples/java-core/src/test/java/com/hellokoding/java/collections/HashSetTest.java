package com.hellokoding.java.collections;

import org.junit.Test;

import java.util.*;

import static org.assertj.core.api.Assertions.assertThat;

public class HashSetTest {
    @Test
    public void declare() {
        Set<Integer> set1 = new HashSet<>();
        assertThat(set1).isInstanceOf(HashSet.class);

        HashSet<Integer> set2 = new HashSet<>();
    }

    @Test
    public void initWithListOfAndSetOf() {
        Set<Integer> set1 = new HashSet<>(List.of(3, 1, 2));
        assertThat(set1).contains(3, 1, 2);

        Set<Integer> set2 = new HashSet<>(Set.of(5, 4, 6));
        assertThat(set2).contains(5, 4, 6);
    }

    @Test
    public void initializeWithAdd() {
        // Create a new HashSet
        Set<Integer> set = new HashSet<>();

        // Add elements to HashSet
        set.add(3);
        set.add(1);
        set.add(2);

        // Can add null element
        set.add(null);

        // Duplicate element will be ignored
        set.add(2);

        assertThat(set).hasSize(4);

        // The output ordering will be vary as Set is not reserved the insertion order
        System.out.println(set);
    }

    @Test
    public void iterate() {
        Set<Integer> set = new HashSet<>(Set.of(3, 1, 2));

        set.forEach(ele -> System.out.printf("%d ", ele));
    }

    @Test
    public void filter() {
        Set<Integer> set = new HashSet<>(Set.of(3, 1, 2));
        Integer[] arr = set.stream().filter(e -> e >= 2).toArray(Integer[]::new);

        assertThat(arr).contains(2, 3);
    }

    @Test
    public void removeIf() {
        Set<Integer> set = new HashSet<>();
        set.add(3);
        set.add(1);
        set.add(2);

        boolean result = set.removeIf(e -> e > 3);
        assertThat(result).isFalse();
        assertThat(set).hasSize(3);

        result = set.removeIf(e -> e > 1);
        assertThat(result).isTrue();
        assertThat(set).hasSize(1);
    }

    @Test
    public void containsAddRemoveSingleElement() {
        Set<Integer> hashSet = new HashSet<>(Set.of(1, 2, 3));
        Boolean contained = hashSet.contains(3);
        assertThat(contained).isTrue();

        hashSet.add(4);
        hashSet.add(4);
        hashSet.add(null);
        assertThat(hashSet).contains(1, 2, 3, 4, null);

        hashSet.remove(2);
        assertThat(hashSet).contains(1, 3, 4, null);
    }

    @Test
    public void containsAddRemoveMultipleElements() {
        Set<Integer> hashSet = new HashSet<>(Set.of(1, 2, 3));
        Boolean contained = hashSet.containsAll(Set.of(1, 2));
        assertThat(contained).isTrue();

        hashSet.addAll(Set.of(3, 4, 5));
        assertThat(hashSet).contains(1, 2, 3, 4, 5);

        hashSet.removeAll(Set.of(1, 2, 6));
        assertThat(hashSet).contains(3, 4, 5);

        hashSet.removeIf(e -> e >= 4);
        assertThat(hashSet).contains(3);
    }

    @Test
    public void objectsComparingProblem(){
        Set<Book> hashSet = new HashSet<>();
        hashSet.add(new Book(1, "a"));

        Boolean contained = hashSet.contains(new Book(1, "a"));
        assertThat(contained).isFalse();

        hashSet.add(new Book(1, "a"));
        assertThat(hashSet).hasSize(2);

        hashSet.remove(new Book(1, "a"));
        assertThat(hashSet).hasSize(2);
    }

    static class Book {
        int id;
        String title;

        Book(int id, String title) {
            this.id = id;
            this.title = title;
        }

        int getId() {
            return id;
        }

        String getTitle() {
            return title;
        }
    }

    @Test
    public void objectsComparingFixed(){
        Set<BookFixed> hashSet = new HashSet<>();
        hashSet.add(new BookFixed(1, "a"));

        Boolean contained = hashSet.contains(new BookFixed(1, "a"));
        assertThat(contained).isTrue();

        hashSet.add(new BookFixed(1, "a"));
        assertThat(hashSet).hasSize(1);

        hashSet.remove(new BookFixed(1, "a"));
        assertThat(hashSet).hasSize(0);
    }

    static class BookFixed {
        int id;
        String title;

        BookFixed(int id, String title) {
            this.id = id;
            this.title = title;
        }

        int getId() {
            return id;
        }

        String getTitle() {
            return title;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            BookFixed bookFixed = (BookFixed) o;
            return id == bookFixed.id &&
                    Objects.equals(title, bookFixed.title);
        }

        @Override
        public int hashCode() {
            return Objects.hash(id, title);
        }
    }

    @Test
    public void sort() {
        Set<Integer> set = new HashSet<>(Set.of(3, 1, 2));
        NavigableSet<Integer> sortedSet = new TreeSet<>(set);

        // Asserts that the set contains the given values in order
        assertThat(sortedSet).containsExactly(1, 2, 3);
    }
}
