package com.hellokoding.java.lang;

import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class StringConcatenationTest {
    @Test
    public void givenMultipleDataTypes_whenConcatByStringBuilder_thenSuccess() {
        StringBuilder sb = new StringBuilder();
        sb.append("Hello ");
        sb.append("Koding ");
        sb.append(2015);
        String result = sb.toString();

        assertThat(result).isEqualTo("Hello Koding 2015");
    }

    @Test
    public void givenMultipleDataTypes_whenConcatByStringBuffer_thenSuccess() {
        StringBuffer sb = new StringBuffer();
        sb.append("Hello ");
        sb.append("Koding ");
        sb.append(2015);
        String result = sb.toString();

        assertThat(result).isEqualTo("Hello Koding 2015");
    }

    @Test
    public void givenMultipleDataTypes_whenConcatByStringConcat_thenSuccess() {
        int foundedYear = 2015;
        String result = "Hello ".concat("Koding ").concat(Integer.toString(foundedYear));

        assertThat(result).isEqualTo("Hello Koding 2015");
    }

    @Test
    public void givenMultipleDataTypes_whenConcatByPlus_thenSuccess() {
        int foundedYear = 2015;
        String result = "Hello " + "Koding " + foundedYear;

        assertThat(result).isEqualTo("Hello Koding 2015");
    }
}
