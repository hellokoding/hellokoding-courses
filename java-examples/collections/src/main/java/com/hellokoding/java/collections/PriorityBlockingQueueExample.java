package com.hellokoding.java.collections;

import java.util.concurrent.PriorityBlockingQueue;
import java.util.concurrent.atomic.AtomicInteger;

public class PriorityBlockingQueueExample {
    private static AtomicInteger seq = new AtomicInteger(0);

    public static void main(String[] args) {
        PriorityBlockingQueue<BookComparable> q = new PriorityBlockingQueue<>(10);

        new Thread(new Consumer(q)).start();
        new Thread(new Producer(q)).start();
    }

    static class Producer implements Runnable {
        private final PriorityBlockingQueue<BookComparable> queue;

        Producer(PriorityBlockingQueue<BookComparable> q) { queue = q; }

        public void run() {
            for (int i = 0; i < 10; i++) {
                queue.put(produce());
            }
        }

        BookComparable produce() {
            BookComparable bookComparable = new BookComparable();
            System.out.println("Put " + bookComparable);

            return bookComparable;
        }
    }

    static class Consumer implements Runnable {
        private final PriorityBlockingQueue<BookComparable> queue;

        Consumer(PriorityBlockingQueue<BookComparable> q) { queue = q; }

        public void run() {
            try {
                while (true) { consume(queue.take()); }
            } catch (InterruptedException ex) {
                System.out.println(ex);
            }
        }

        void consume(BookComparable x) {
            System.out.println("Take " + x);
        }
    }

    static class BookComparable implements Comparable<BookComparable> {
        Integer id ;

        BookComparable() {
            this.id = seq.incrementAndGet();
        }

        @Override
        public int compareTo(BookComparable o) {
            return o.id.compareTo(this.id);
        }

        @Override
        public String toString() {
            return this.id.toString();
        }
    }
}
