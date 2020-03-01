package com.hellokoding.java.java14;

import org.junit.Test;

public class HelpfulNullPointerException {
    public void nullString(Pojo pojo) {
        pojo.field1 = "a";
    }

    @Test
    public void testNullString() {
        nullString(null);
    }
}

class Pojo {
    String field1;
}
