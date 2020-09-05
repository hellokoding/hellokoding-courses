package com.hellokoding.springboot.oauth2;

import java.time.Duration;

public interface UserSession {
    void put(String key, Object value, Duration timeout);
    void put(String key, Object value);
    Object get(String key);
    void invalidate();
}
