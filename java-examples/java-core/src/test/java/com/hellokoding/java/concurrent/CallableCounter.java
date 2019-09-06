package com.hellokoding.java.concurrent;

import java.util.concurrent.Callable;
import java.util.concurrent.atomic.AtomicInteger;

public class CallableCounter implements Callable<AtomicInteger> {
    private AtomicInteger counter;

    CallableCounter(AtomicInteger counter) {
        this.counter = counter;
    }

    @Override
    public AtomicInteger call() throws Exception {
        for (int i = 0; i < 100; i++) {
            counter.getAndIncrement();
            Thread.sleep(10);
        }

        return counter;
    }
}
