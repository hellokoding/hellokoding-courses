package com.hellokoding.java.concurrent;

import org.junit.Test;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicInteger;

import static org.assertj.core.api.Assertions.assertThat;

public class CallableTest {
    @Test
    public void testCallable() throws ExecutionException, InterruptedException {
        AtomicInteger counter = new AtomicInteger();
        ExecutorService executorService = Executors.newFixedThreadPool(10);

        AtomicInteger result = executorService.submit(new CallableCounter(counter)).get();
        assertThat(result.get()).isEqualTo(100);

        executorService.shutdown();
    }
}

