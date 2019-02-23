package com.hellokoding.java.collections;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.atomic.AtomicInteger;

public class BlockingQueueExample {
    private static AtomicInteger seq = new AtomicInteger(0);

    public static void main(String[] args) {
        BlockingQueue q = new ArrayBlockingQueue(10);
        Producer p = new Producer(q);
        Consumer c1 = new Consumer(q);
        Consumer c2 = new Consumer(q);
        new Thread(p).start();
        new Thread(c1).start();
        new Thread(c2).start();
    }

    static class Producer implements Runnable {
        private final BlockingQueue queue;
        Producer(BlockingQueue q) { queue = q; }
        public void run() {
            try {
                for (int i = 0; i < 10; i++) {
                    queue.put(produce());
                }
            } catch (InterruptedException ex) {System.out.println(ex);}
        }
        Book produce() {
            Book bookNonComparable = new Book();
            System.out.println("Put " + bookNonComparable);

            return bookNonComparable;
        }
    }

    static class Consumer implements Runnable {
        private final BlockingQueue queue;
        Consumer(BlockingQueue q) { queue = q; }
        public void run() {
            try {
                while (true) { consume(queue.take()); }
            } catch (InterruptedException ex) {
                System.out.println(ex);
            }
        }
        void consume(Object x) {System.out.println(x);}
    }

    static class Book {
        Integer id ;

        Book() {
            this.id = seq.incrementAndGet();
        }

        @Override
        public String toString() {
            return this.id.toString();
        }
    }
}


