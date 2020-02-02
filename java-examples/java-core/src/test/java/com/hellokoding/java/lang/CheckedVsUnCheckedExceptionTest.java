package com.hellokoding.java.lang;

import org.junit.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class CheckedVsUnCheckedExceptionTest {
    @Test
    public void tryCatchException() {
        try {
            Files.readAllLines(Path.of("test.txt"));
        } catch (IOException e) {
            System.out.println(e);
        }
    }

    @Test
    public void throwsException() throws IOException {
        Files.readAllLines(Path.of("test.txt"));
    }

    @Test
    public void tryCatchRuntimeException() {
        try {
            Integer.parseInt("s");
        } catch (NumberFormatException e) {
            System.out.println(e);
        }
    }

    @Test
    public void throwsRuntimeException() throws NumberFormatException {
        Integer.parseInt("s");
    }
}
