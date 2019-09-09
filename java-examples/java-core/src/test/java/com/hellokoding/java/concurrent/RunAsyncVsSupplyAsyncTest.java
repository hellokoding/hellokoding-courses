package com.hellokoding.java.concurrent;

import org.junit.Test;

import java.util.concurrent.*;

import static com.hellokoding.java.concurrent.ConcurrentUtils.stop;
import static org.assertj.core.api.Assertions.assertThat;

public class RunAsyncVsSupplyAsyncTest {
    ExecutorService executorService = Executors.newFixedThreadPool(3);

    @Test
    public void runAsync() {
        Counter counter = new Counter();

        for (int i = 0; i < 10000; i++) {
            CompletableFuture.runAsync(counter::increaseRunnable)
            .thenRunAsync(counter::increaseRunnable);
        }

        stop(ForkJoinPool.commonPool());

        assertThat(counter.count).isEqualTo(10000);
    }

    @Test
    public void runAsyncWithExecutorService() {
        Counter counter = new Counter();

        for (int i = 0; i < 10000; i++) {
            CompletableFuture.runAsync(counter::increaseRunnable, executorService);
        }

        stop(executorService);

        assertThat(counter.count).isEqualTo(10000);
    }

    @Test
    public void supplyAsync() {
        Counter counter = new Counter();

        for (int i = 0; i < 10000; i++) {
            CompletableFuture.supplyAsync(counter::increaseSupplier, executorService);
        }

        stop(executorService);

        assertThat(counter.count).isEqualTo(10000);
    }
}

class Counter {
    int count = 0;

    synchronized void increaseRunnable() {
        count++;
    }

    synchronized int increaseSupplier() {
        count++;

        return count;
    }
}
