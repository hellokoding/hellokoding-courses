package com.hellokoding.java.collections;

import org.junit.Test;

import java.util.*;

import static org.assertj.core.api.Assertions.assertThat;

public class TreeSetTest {
    @Test
    public void declare() {
        NavigableSet<Integer> treeSet1 = new TreeSet<>();
        assertThat(treeSet1).isInstanceOf(TreeSet.class);

        TreeSet<Integer> treeSet2 = new TreeSet<>();
    }

    @Test
    public void initWithComparable() {
        NavigableSet<Integer> treeSet1 = new TreeSet<>(Set.of(3, 1, 2));
        assertThat(treeSet1).containsExactly(1, 2, 3);

        NavigableSet<Integer> treeSet2 = new TreeSet<>();
        treeSet2.add(1);
        treeSet2.add(1); // duplicated element is ignored
        treeSet2.addAll(treeSet1);
        assertThat(treeSet2).containsExactly(1, 2, 3);
    }

    @Test
    public void initWithComparator() {
        NavigableSet<Integer> treeSet = new TreeSet<>(Comparator.reverseOrder());
        treeSet.addAll(List.of(1, 2, 3));

        assertThat(treeSet).containsExactly(3, 2, 1);
    }

    @Test
    public void iterateWithForEachConsumer() {
        NavigableSet<Integer> treeSet = new TreeSet<>(Set.of(3, 1, 2));
        treeSet.forEach(e -> System.out.printf("%d ", e));
    }

    @Test
    public void iterateWithIterator() {
        NavigableSet<Integer> treeSet = new TreeSet<>(Set.of(1, 2, 3));

        Iterator<Integer> iterator = treeSet.iterator();
        while (iterator.hasNext()) {
            System.out.printf("%d ", iterator.next());
        }

        Iterator<Integer> descendingIterator = treeSet.descendingIterator();
        while (descendingIterator.hasNext()) {
            System.out.printf("%d ", descendingIterator.next());
        }
    }

    @Test
    public void retrieve() {
        NavigableSet<Integer> treeSet = new TreeSet<>(Set.of(3, 1, 2));
        int first = treeSet.first();
        assertThat(first).isEqualTo(1);

        int last = treeSet.last();
        assertThat(last).isEqualTo(3);

        NavigableSet<Integer> descendingSet = treeSet.descendingSet();
        assertThat(descendingSet).containsExactly(3, 2, 1);
    }

    @Test
    public void retrieveWithStreamAPI() {
        NavigableSet<Integer> treeSet = new TreeSet<>(List.of(1, 2, 3));
        Integer firstElement = treeSet.stream().findFirst().orElse(null);
        assertThat(firstElement).isEqualTo(1);

        Integer[] elements = treeSet.stream().filter(e -> e >= 2).toArray(Integer[]::new);
        assertThat(elements).contains(2, 3);
    }

    @Test
    public void examine() {
        NavigableSet<Integer> treeSet = new TreeSet<>(Set.of(1, 2, 3));
        Boolean contained = treeSet.contains(3);
        assertThat(contained).isTrue();
    }

    @Test
    public void containsACustomObject(){
        TreeSet<Book> treeSet = new TreeSet<>();
        treeSet.add(new Book(1, "a"));

        Boolean contained = treeSet.contains(new Book(2, "a"));
        assertThat(contained).isTrue();
    }

    @Test
    public void add() {
        NavigableSet<Integer> treeSet = new TreeSet<>();
        treeSet.add(1);
        treeSet.add(1); // duplicated element is ignored
        treeSet.addAll(Set.of(2, 3));

        assertThat(treeSet).containsExactly(1, 2, 3);
    }

    @Test
    public void addACustomObject() {
        NavigableSet<Book> treeSet = new TreeSet<>();
        treeSet.add(new Book(1, "a"));
        treeSet.add(new Book(2, "a"));

        assertThat(treeSet).hasSize(1);
    }

    @Test
    public void remove() {
        NavigableSet<Integer> treeSet = new TreeSet<>(Set.of(1, 2, 3));
        treeSet.remove(1);

        assertThat(treeSet).containsExactly(2, 3);
    }

    @Test
    public void removeACustomObject() {
        NavigableSet<Book> treeSet = new TreeSet<>();
        treeSet.add(new Book(1, "a"));
        treeSet.remove(new Book(2, "a"));

        assertThat(treeSet).isEmpty();
    }

    static class Book  implements Comparable<Book> {
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

        @Override
        public int compareTo(Book o) {
            return CharSequence.compare(this.title, o.title);
        }
    }
}
