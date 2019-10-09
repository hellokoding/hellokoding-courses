package com.hellokoding.java.collections;

import org.assertj.core.api.ThrowableAssert;
import org.junit.Test;

import java.util.HashSet;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class Java9CollectionFactoryMethodsTest {
    @Test
    public void initNullWithListOfAndSetOf() {
        String s = null;
        ThrowableAssert.ThrowingCallable c = () -> new HashSet<>(List.of(s));

        assertThatThrownBy(c).isInstanceOf(NullPointerException.class);
    }
}
