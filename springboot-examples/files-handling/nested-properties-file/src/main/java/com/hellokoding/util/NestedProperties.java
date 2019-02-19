package com.hellokoding.util;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "nested")
public class NestedProperties {
    public static class A {
        private String b;

        public String getB() {
            return b;
        }

        public void setB(String b) {
            this.b = b;
        }
    }

    private A a;

    public A getA() {
        return a;
    }

    public void setA(A a) {
        this.a = a;
    }
}
