package com.hellokoding.java.java14;

import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class Records {
    @Test
    public void testRecord() {
        Book b = new Book(1, "Java 14");
        int id = b.id();
        String title = b.title();

        assertThat(id).isEqualTo(1);
        assertThat(title).isEqualTo("Java 14");
    }
}

record Book(int id, String title) {
}
