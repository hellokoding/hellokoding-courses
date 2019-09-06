package com.hellokoding.java.collections;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.atomic.AtomicInteger;

public class BlockingQueueExample {
    private static AtomicInteger seq = new AtomicInteger(0);

    public static void main(String[] args) throws InterruptedException {
        BlockingQueue q = new ArrayBlockingQueue(10);

        Thread t1 = new Thread(new Producer(q));
        Thread t2 = new Thread(new Consumer(q));
        Thread t3 = new Thread(new Consumer(q));
        t1.start();
        t2.start();
        t3.start();

        t1.join();
        t2.join();
        t3.join();
    }

    static class Producer implements Runnable {
        private final BlockingQueue queue;
        Producer(BlockingQueue q) { queue = q; }
        public void run() {
            try {
                for (int i = 0; i < 10; i++) {
                    queue.put(produce());
                    Thread.sleep(10);
                }
            } catch (InterruptedException ex) {System.out.println(ex);}
        }
        Book produce() {
            Book book = new Book();
            System.out.printf("Put %s %s", book, System.lineSeparator());

            return book;
        }
    }

    static class Consumer implements Runnable {
        private final BlockingQueue queue;
        Consumer(BlockingQueue q) { queue = q; }
        public void run() {
            try {
                while (true) {
                    consume(queue.take());
                    Thread.sleep(10);
                }
            } catch (InterruptedException ex) {
                System.out.println(ex);
            }
        }
        void consume(Object x) {
            System.out.printf("Take %s %s", x, System.lineSeparator());
        }
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


