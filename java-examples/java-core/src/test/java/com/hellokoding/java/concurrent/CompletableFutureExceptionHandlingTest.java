package com.hellokoding.java.concurrent;

import org.junit.Test;

import java.util.concurrent.CompletableFuture;

public class CompletableFutureExceptionHandlingTest {
    @Test
    public void handleWithException() {
        CompletableFuture completableFuture = CompletableFuture
            .supplyAsync(() -> Integer.parseInt("s"))
            .handleAsync((result, e) -> {
                if (e != null) {
                    System.out.println(e.getMessage());
                    return "Error!";
                } else {
                    return result + 1;
                }
            })
            .thenAcceptAsync(System.out::println);
    }

    @Test
    public void handleWithoutExceptions() {
        CompletableFuture completableFuture = CompletableFuture
            .supplyAsync(() -> 1)
            .handleAsync((result, e) -> {
                if (e != null) {
                    System.out.println(e.getMessage());
                    return "Error!";
                } else {
                    return result + 1;
                }
            })
            .thenAcceptAsync(System.out::println);
    }

    @Test
    public void exceptionallyWithException() {
        CompletableFuture completableFuture = CompletableFuture
            .supplyAsync(() -> Integer.parseInt("s"))
            .exceptionallyAsync((e) -> {
                System.out.println(e.getMessage());
                return 0;
            })
            .thenAcceptAsync(System.out::println);
    }

    @Test
    public void exceptionallyWithoutExceptions() {
        CompletableFuture completableFuture = CompletableFuture
            .supplyAsync(() -> 1)
            .exceptionallyAsync((e) -> {
                System.out.println(e.getMessage());
                return 0;
            })
            .thenAcceptAsync(System.out::println);
    }

    @Test
    public void whenCompleteWithException() {
        CompletableFuture completableFuture = CompletableFuture
            .supplyAsync(() -> Integer.parseInt("s"))
            .whenCompleteAsync((result, e) -> {
                if (e != null) System.out.println(e.getMessage());
            })
            .thenAcceptAsync(System.out::println);
    }

    @Test
    public void whenCompleteWithoutExceptions() {
        CompletableFuture completableFuture = CompletableFuture
            .supplyAsync(() -> 1)
            .whenCompleteAsync((result, e) -> {
                if (e != null) System.out.println(e.getMessage());
            })
            .thenAcceptAsync(System.out::println);
    }
}
