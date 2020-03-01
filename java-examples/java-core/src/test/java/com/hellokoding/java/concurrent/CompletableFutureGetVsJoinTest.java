package com.hellokoding.java.concurrent;

import org.junit.Test;

import java.util.concurrent.CompletableFuture;

public class CompletableFutureGetVsJoinTest {
    @Test
    public void handleWithException() {
        CompletableFuture completableFuture = CompletableFuture
            .supplyAsync(() -> Integer.parseInt("s"))
            .thenAcceptAsync(System.out::println);
    }
}
