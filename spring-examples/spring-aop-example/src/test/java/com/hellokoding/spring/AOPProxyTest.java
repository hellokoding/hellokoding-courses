package com.hellokoding.spring;

import org.aopalliance.aop.Advice;
import org.junit.Test;
import org.springframework.aop.framework.ProxyFactory;

import static org.assertj.core.api.Assertions.assertThat;

public class AOPProxyTest {
    @Test
    public void callDirectly() {
        Foo foo = new Foo();
        String s = foo.fooBar();

        assertThat(s).isEqualTo("foo");
    }

    @Test
    public void callOnProxy() {
        ProxyFactory factory = new ProxyFactory(new Foo());
        //factory.addInterface(FooInterface.class);
        //factory.addAdvisor(new LoggingAspect());

        Foo pojo = (Foo) factory.getProxy();
        String s = pojo.fooBar();

        assertThat(s).isEqualTo("foobar");
    }
}
