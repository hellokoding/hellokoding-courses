package com.hellokoding.java.concurrent;

import org.junit.Test;

import java.util.concurrent.CompletableFuture;

import static org.assertj.core.api.Java6Assertions.assertThat;

public class CompletableFutureExceptionHandlingTest {
    @Test
    public void handle() {
        CompletableFuture completableFuture = CompletableFuture
            .supplyAsync(() -> Integer.parseInt("s"))
            .handleAsync((result, e) -> {
                if (e != null) {
                    System.out.println(e.getMessage());
                    return "Error!";
                } else {
                    return result;
                }
            })
            .thenAcceptAsync(System.out::println);

        assertThat(completableFuture.join())
            .isNull();
    }

    @Test
    public void exceptionally() {
        CompletableFuture completableFuture = CompletableFuture
            .supplyAsync(() -> Integer.parseInt("s"))
            .exceptionallyAsync((e) -> {
                System.out.println(e.getMessage());
                return null;
            })
            .thenAcceptAsync(System.out::println);

        assertThat(completableFuture.join())
            .isNull();
    }

    @Test
    public void whenComplete() {
        CompletableFuture completableFuture = CompletableFuture
            .supplyAsync(() -> Integer.parseInt("s"))
            .whenCompleteAsync((result, e) -> {
                if (e != null) System.out.println(e.getMessage());
            })
            .thenAcceptAsync(System.out::println);

        assertThat(completableFuture.join())
            .isNull();
    }
}
