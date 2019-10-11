package com.hellokoding.java.collections;

import org.junit.Test;

import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

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
    public void containsAddRemove() {
        Set<Integer> linkedHashSet = new LinkedHashSet<>(List.of(1, 2, 3));
        Boolean contained = linkedHashSet.contains(3);
        assertThat(contained).isTrue();

        linkedHashSet.add(1); // duplicated element is ignored
        linkedHashSet.add(null); // permits null element
        assertThat(linkedHashSet).containsExactly(1, 2, 3, null);

        linkedHashSet.remove(2);
        assertThat(linkedHashSet).containsExactly(1, 3, null);
    }

    @Test
    public void containsAddRemoveACustomObject(){
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
}
