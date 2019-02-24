package com.hellokoding.java.collections;

import java.util.concurrent.PriorityBlockingQueue;
import java.util.concurrent.atomic.AtomicInteger;

public class PriorityBlockingQueueExample {
    private static AtomicInteger seq = new AtomicInteger(0);

    public static void main(String[] args) throws InterruptedException {
        PriorityBlockingQueue<BookComparable> q = new PriorityBlockingQueue<>(10);

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
        private final PriorityBlockingQueue<BookComparable> queue;

        Producer(PriorityBlockingQueue<BookComparable> q) { queue = q; }

        public void run() {
            try {
                for (int i = 0; i < 10; i++) {
                    queue.put(produce());
                    Thread.sleep(10);
                }
            } catch(InterruptedException ex){
                System.out.println(ex);
            }
        }

        BookComparable produce() {
            BookComparable bookComparable = new BookComparable();
            System.out.printf("Put %s %s", bookComparable, System.lineSeparator());

            return bookComparable;
        }
    }

    static class Consumer implements Runnable {
        private final PriorityBlockingQueue<BookComparable> queue;

        Consumer(PriorityBlockingQueue<BookComparable> q) { queue = q; }

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

        void consume(BookComparable bookComparable) {
            System.out.printf("Take %s %s", bookComparable, System.lineSeparator());
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
