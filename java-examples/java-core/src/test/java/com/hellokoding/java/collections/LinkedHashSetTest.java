package com.hellokoding.java.collections;

import org.junit.Test;

import java.util.*;

import static org.assertj.core.api.Assertions.assertThat;

public class LinkedHashSetTest {
    @Test
    public void declare() {
        Set<Integer> linkedHashSet1 = new LinkedHashSet<>();
        assertThat(linkedHashSet1).isInstanceOf(LinkedHashSet.class);

        LinkedHashSet<Integer> linkedHashSet2 = new LinkedHashSet<>();
    }

    @Test
    public void initInOneLine() {
        Set<Integer> linkedHashSet1 = new LinkedHashSet<>(Set.of(1, 2, 3));
        assertThat(linkedHashSet1).contains(1, 2, 3);

        Set<Integer> linkedHashSet2 = new LinkedHashSet<>(List.of(1, 2, 3));
        assertThat(linkedHashSet2).containsExactly(1, 2, 3);
    }

    @Test
    public void initInOneLineWithDoubleBrace() {
        Set<Integer> linkedHashSet = new LinkedHashSet<>(){{
            add(1);
            add(2);
            add(3);
        }};

        assertThat(linkedHashSet).hasSize(3);
    }

    @Test
    public void initWithAddAndAddAll() {
        Set<Integer> linkedHashSet1 = new LinkedHashSet<>(Set.of(1, 2, 3));

        Set<Integer> linkedHashSet2 = new LinkedHashSet<>();
        linkedHashSet2.add(1);
        linkedHashSet2.add(1); // duplicated element is ignored
        linkedHashSet2.add(null); // permits null element

        linkedHashSet2.addAll(linkedHashSet1);

        assertThat(linkedHashSet2).hasSize(4);
    }

    @Test
    public void iterateWithForEachConsumer() {
        Set<Integer> linkedHashSet = new LinkedHashSet<>(Set.of(1, 2, 3));
        linkedHashSet.forEach(e -> System.out.printf("%d ", e));
    }

    @Test
    public void iterateWithIterator() {
        Set<Integer> linkedHashSet = new LinkedHashSet<>(Set.of(1, 2, 3));

        Iterator<Integer> iterator = linkedHashSet.iterator();
        while (iterator.hasNext()) {
            System.out.printf("%d ", iterator.next());
        }
    }

    @Test
    public void retrieve() {
        Set<Integer> linkedHashSet = new LinkedHashSet<>(List.of(1, 2, 3));
        Integer firstElement = linkedHashSet.stream().findFirst().orElse(null);
        assertThat(firstElement).isEqualTo(1);

        Integer[] elements = linkedHashSet.stream().filter(e -> e >= 2).toArray(Integer[]::new);
        assertThat(elements).contains(2, 3);
    }

    @Test
    public void containsAddRemoveSingleElement() {
        Set<Integer> hashSet = new LinkedHashSet<>(Set.of(1, 2, 3));
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
        Set<Integer> linkedHashSet = new LinkedHashSet<>(Set.of(1, 2, 3));
        Boolean contained = linkedHashSet.containsAll(Set.of(1, 2));
        assertThat(contained).isTrue();

        linkedHashSet.addAll(Set.of(3, 4, 5));
        assertThat(linkedHashSet).contains(1, 2, 3, 4, 5);

        linkedHashSet.removeAll(Set.of(1, 2, 6));
        assertThat(linkedHashSet).contains(3, 4, 5);

        linkedHashSet.removeIf(e -> e >= 4);
        assertThat(linkedHashSet).contains(3);
    }

    @Test
    public void objectsComparingProblem(){
        Set<Book> linkedHashSet = new LinkedHashSet<>();
        linkedHashSet.add(new Book(1, "a"));

        Boolean contained = linkedHashSet.contains(new Book(1, "a"));
        assertThat(contained).isFalse();

        linkedHashSet.add(new Book(1, "a"));
        assertThat(linkedHashSet).hasSize(2);

        linkedHashSet.remove(new Book(1, "a"));
        assertThat(linkedHashSet).hasSize(2);
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
        Set<BookFixed> linkedHashSet = new LinkedHashSet<>();
        linkedHashSet.add(new BookFixed(1, "a"));

        Boolean contained = linkedHashSet.contains(new BookFixed(1, "a"));
        assertThat(contained).isTrue();

        linkedHashSet.add(new BookFixed(1, "a"));
        assertThat(linkedHashSet).hasSize(1);

        linkedHashSet.remove(new BookFixed(1, "a"));
        assertThat(linkedHashSet).hasSize(0);
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
        Set<Integer> set = new LinkedHashSet<>(Set.of(3, 1, 2));
        NavigableSet<Integer> sortedSet = new TreeSet<>(set);

        // Asserts that the set contains the given values in order
        assertThat(sortedSet).containsExactly(1, 2, 3);
    }
}
