package com.hellokoding.spring;

public class Foo {
    public String fooBar() {
        return "foo" + this.bar();
    }

    private String fooPrivate() {
        return "foo";
    }

    private String bar() {
        return "bar";
    }
}

interface FooInterface {
    String foo();
}
