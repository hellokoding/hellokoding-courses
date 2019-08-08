package com.hellokoding.springboot.caching.configuration;

import org.springframework.cache.interceptor.KeyGenerator;
import org.springframework.util.StringUtils;

import java.lang.reflect.Method;

public class CustomKeyGenerator implements KeyGenerator {
    @Override
    public Object generate(Object o, Method method, Object... params) {
        return String.format("%s_%s_%s",
            o.getClass().getSimpleName(),
            method.getName(),
            StringUtils.arrayToDelimitedString(params, "_"));
    }
}
