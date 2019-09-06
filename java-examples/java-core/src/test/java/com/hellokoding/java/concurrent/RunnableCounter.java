package com.hellokoding.java.concurrent;

import java.util.concurrent.atomic.AtomicInteger;

public class RunnableCounter implements Runnable{
    private AtomicInteger counter;

    RunnableCounter(AtomicInteger counter) {
        this.counter = counter;
    }

    @Override
    public void run() {
        for (int i = 0; i < 100; i++) {
            counter.getAndIncrement();
            try {
                Thread.sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
