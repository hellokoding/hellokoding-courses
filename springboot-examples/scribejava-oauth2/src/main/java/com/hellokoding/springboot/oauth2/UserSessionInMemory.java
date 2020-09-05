package com.hellokoding.springboot.oauth2;

import lombok.RequiredArgsConstructor;

import javax.servlet.http.HttpServletRequest;
import java.time.Duration;

@RequiredArgsConstructor
public class UserSessionInMemory implements UserSession {
    private final HttpServletRequest httpServletRequest;

    @Override
    public void put(String key, Object value, Duration timeout) {
        httpServletRequest.getSession().setAttribute(key, value);
    }

    @Override
    public void put(String key, Object value) {
        httpServletRequest.getSession().setAttribute(key, value);
    }

    @Override
    public Object get(String key) {
        return httpServletRequest.getSession().getAttribute(key);
    }

    @Override
    public void invalidate() {
        httpServletRequest.getSession().invalidate();
    }
}
