package com.hellokoding.java.concurrent;

import org.junit.Test;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import static com.hellokoding.java.concurrent.ConcurrentUtils.stop;
import static org.assertj.core.api.Assertions.assertThat;

public class RunAsyncVsSupplyAsyncTest {
    private ExecutorService executorService = Executors.newFixedThreadPool(2);

    @Test
    public void runAsync() throws ExecutionException, InterruptedException {
        CompletableFuture c = CompletableFuture
            .runAsync(() -> System.out.println("runAsync has no return values"));

        assertThat(c.get()).isNull();
    }

    @Test
    public void runAsyncWithCallbacks() throws ExecutionException, InterruptedException {
        CompletableFuture c = CompletableFuture
            .runAsync(() -> System.out.println("runAsync"))
            .thenRunAsync(() -> System.out.println("thenRunAsync callback"));

        assertThat(c.get()).isNull();
    }

    @Test
    public void runAsyncWithExecutor() throws ExecutionException, InterruptedException {
        CompletableFuture c = CompletableFuture
            .runAsync(() -> System.out.println("Run runAsync with an Executor"), executorService);

        stop(executorService);

        assertThat(c.get()).isNull();
    }

    @Test
    public void supplyAsync() throws ExecutionException, InterruptedException {
        CompletableFuture c = CompletableFuture
            .supplyAsync(() -> "supplyAsync has return value");

        assertThat(c.get()).isEqualTo("supplyAsync has return value");
    }

    @Test
    public void supplyAsyncWithCallbacks() throws ExecutionException, InterruptedException {
        CompletableFuture c = CompletableFuture
            .supplyAsync(() -> "supplyAsync")
            .thenApplyAsync((s) -> s + " callback");

        assertThat(c.get()).isEqualTo("supplyAsync callback");
    }

    @Test
    public void supplyAsyncWithExecutor() throws ExecutionException, InterruptedException {
        CompletableFuture c = CompletableFuture
            .supplyAsync(() -> "run supplyAsync with an Executor", executorService);

        stop(executorService);

        assertThat(c.get()).isEqualTo("run supplyAsync with an Executor");
    }
}
