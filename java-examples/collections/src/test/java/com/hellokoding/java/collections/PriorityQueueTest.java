package com.hellokoding.java.collections;

import org.junit.Assert;
import org.junit.Test;

import java.util.Comparator;
import java.util.PriorityQueue;

public class PriorityQueueTest {
    @Test(expected = ClassCastException.class)
    public void whenAddNonComparable_thenThrowException(){
        PriorityQueue<BookNonComparable> books = new PriorityQueue<>();
        books.add(new BookNonComparable("C"));
        books.add(new BookNonComparable("A"));
        books.add(new BookNonComparable("D"));
    }

    @Test
    public void whenAddComparable_thenSuccess(){
        PriorityQueue<BookComparable> books = new PriorityQueue<>();
        books.add(new BookComparable("C"));
        books.add(new BookComparable("A"));
        books.add(new BookComparable("D"));

        Assert.assertEquals("A", books.peek().title);
        Assert.assertEquals("A", books.remove().title);
        Assert.assertEquals("C", books.peek().title);
    }

    @Test
    public void whenAddComparator_thenSuccess(){
        Comparator<BookNonComparable> comparator = Comparator.comparing(BookNonComparable::getTitle);
        PriorityQueue<BookNonComparable> books = new PriorityQueue<>(comparator);
        books.offer(new BookNonComparable("C"));
        books.offer(new BookNonComparable("A"));
        books.offer(new BookNonComparable("D"));

        Assert.assertEquals("A", books.peek().title);
        Assert.assertEquals("A", books.poll().title);
        Assert.assertEquals("C", books.peek().title);
    }

    @Test(expected = NullPointerException.class)
    public void whenAddNull_thenThrowException(){
        PriorityQueue<BookNonComparable> books = new PriorityQueue<>();
        books.add(null);
    }

    static class BookComparable implements Comparable<BookComparable> {
        String title;

        BookComparable(String title) {
            this.title = title;
        }

        @Override
        public int compareTo(BookComparable o) {
            return this.title.compareTo(o.title);
        }
    }

    static class BookNonComparable {
        String title;

        BookNonComparable(String title) {
            this.title = title;
        }

        public String getTitle(){
            return this.title;
        }

        @Override
        public String toString() {
            return this.title;
        }
    }
}
