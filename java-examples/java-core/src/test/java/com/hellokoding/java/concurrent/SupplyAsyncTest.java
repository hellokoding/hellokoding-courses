package com.hellokoding.java.concurrent;

import org.junit.Test;

import java.util.Arrays;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.stream.Collectors;

import static com.hellokoding.java.concurrent.ConcurrentUtils.stop;
import static org.assertj.core.api.Assertions.assertThat;

public class SupplyAsyncTest {
    @Test
    public void supplyAsync() throws ExecutionException, InterruptedException {
        CompletableFuture completableFuture = CompletableFuture.supplyAsync(() -> "Hello, Future!");

        assertThat(completableFuture.get()).isEqualTo("Hello, Future!");
    }

    @Test
    public void thenApplyAsync() throws ExecutionException, InterruptedException {
        CompletableFuture<String> completableFuture = CompletableFuture.supplyAsync(() -> "Future");

        completableFuture = completableFuture.thenApplyAsync((s) -> s.concat(" is awesome!"));

        assertThat(completableFuture.get()).isEqualTo("Future is awesome!");
    }

    @Test
    public void thenApplyAsyncWithExecutor() throws ExecutionException, InterruptedException {
        ExecutorService executorService = Executors.newFixedThreadPool(2);

        CompletableFuture<String> completableFuture = CompletableFuture.supplyAsync(() -> "Future", executorService);

        completableFuture = completableFuture.thenApplyAsync((s) -> s.concat(" is awesome!"), executorService);

        stop(executorService);

        assertThat(completableFuture.get()).isEqualTo("Future is awesome!");
    }

    @Test
    public void thenComposeAsync() throws ExecutionException, InterruptedException {
        CompletableFuture<String> composedCompletableFuture = CompletableFuture
                .supplyAsync(() -> "Future")
                .thenComposeAsync(s -> CompletableFuture.supplyAsync(() -> s.concat(" is awesome!")));

        assertThat(composedCompletableFuture.get()).isEqualTo("Future is awesome!");
    }

    @Test
    public void thenCombineAsync() throws ExecutionException, InterruptedException {
        CompletableFuture<String> completableFuture1 = CompletableFuture.supplyAsync(() -> "Future");
        CompletableFuture<String> completableFuture2 = CompletableFuture.supplyAsync(() -> " is awesome!");

        CompletableFuture<String> combinedCompletableFuture = completableFuture1.thenCombineAsync(completableFuture2, (s1, s2) -> s1.concat(s2));

        assertThat(combinedCompletableFuture.get()).isEqualTo("Future is awesome!");
    }

    @Test
    public void allOf() {
        CompletableFuture<String> cf1 = CompletableFuture.supplyAsync(() -> "Future");
        CompletableFuture<String> cf2 = CompletableFuture.supplyAsync(() -> " is awesome!");
        CompletableFuture<String> cf3 = CompletableFuture.supplyAsync(() -> "!");
        CompletableFuture<String>[] cfs = new CompletableFuture[]{cf1, cf2, cf3};

        CompletableFuture<Void> allCf = CompletableFuture.allOf(cfs);
        allCf.join();

        String result = Arrays.stream(cfs).map(CompletableFuture::join).collect(Collectors.joining());
        assertThat(result).isEqualTo("Future is awesome!!");
    }

    @Test
    public void anyOf() throws ExecutionException, InterruptedException {
        CompletableFuture<String> cf1 = CompletableFuture.supplyAsync(() -> "Future");
        CompletableFuture<String> cf2 = CompletableFuture.supplyAsync(() -> " is awesome!");
        CompletableFuture<String> cf3 = CompletableFuture.supplyAsync(() -> "!");

        CompletableFuture<Object> anyCf = CompletableFuture.anyOf(cf1, cf2, cf3);
        System.out.println(anyCf.get());

        assertThat(anyCf).isDone();
    }

    @Test
    public void thenAcceptAsync() throws ExecutionException, InterruptedException {
        CompletableFuture<String> completableFuture = CompletableFuture.supplyAsync(() -> "Future");

        completableFuture = completableFuture.thenApplyAsync((s) -> s.concat(" is awesome!"));
        CompletableFuture<Void> procedureFuture = completableFuture.thenAcceptAsync(System.out::println);

        assertThat(procedureFuture.get()).isNull();
    }

    @Test
    public void thenRunAsync() throws ExecutionException, InterruptedException {
        CompletableFuture<String> completableFuture = CompletableFuture.supplyAsync(() -> "Future");

        completableFuture = completableFuture.thenApplyAsync((s) -> s.concat(" is awesome!"));
        CompletableFuture<Void> procedureFuture = completableFuture.thenRunAsync(() -> System.out.println("!"));

        assertThat(procedureFuture.get()).isNull();
    }
}
