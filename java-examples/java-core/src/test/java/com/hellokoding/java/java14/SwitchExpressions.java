package com.hellokoding.java.java14;

import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class SwitchExpressions {
    public String switchStatementWithBreak(int val) {
        var str = "";
        switch (val) {
            case 0:
                str = "zero";
                break;
            case 1:
                str = "one";
                break;
            default:
                System.out.println("default");
                str = "many";
                break;
        }

        return str;
    }

    public String toWord(int val) {
        var str = switch (val) {
            case 0 -> "zero";
            case 1 -> "one";
            default -> {
                System.out.println("default");
                yield "many";
            }
        };

        return str;
    }

    @Test
    public void testSwitchExpressions() {
        var str1 = toWord (0);
        var str2 = toWord (1);
        var str3 = toWord (2);

        assertThat(str1).isEqualTo("zero");
        assertThat(str2).isEqualTo("one");
        assertThat(str3).isEqualTo("many");
    }
}
