package com.hellokoding.java.lang;

import org.junit.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.function.Consumer;
import static com.hellokoding.java.lang.ThrowingConsumer.wrapper;

public class LambdaExceptionTest {
    @Test
    public void tryCatchInLambda() {
        List.of("test.txt", "test2.txt").forEach(item -> {
            try {
                Files.readAllLines(Path.of(item));
            } catch (IOException e) {
                System.out.println(e);
                throw new RuntimeException(e);
            }
        });
    }

    @Test
    public void wrapperMethod() {
        List.of("test.txt", "test2.txt")
            .forEach(item -> readFile(item));
    }

    void readFile(String fileName) throws RuntimeException{
        try {
            Files.readAllLines(Path.of(fileName));
        } catch (IOException e) {
            System.out.println(e);
            throw new RuntimeException(e);
        }
    }

    @Test
    public void genericWrapperMethod() {
        List.of("test.txt", "test2.txt")
            .forEach(wrapper(item -> Files.readAllLines(Path.of(item))));
    }
}

@FunctionalInterface
interface ThrowingConsumer<T, E extends Exception> {
    void accept(T t) throws E;

    static <T> Consumer<T> wrapper(ThrowingConsumer<T, Exception> t) {
        return arg -> {
            try {
                t.accept(arg);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        };
    }
}
