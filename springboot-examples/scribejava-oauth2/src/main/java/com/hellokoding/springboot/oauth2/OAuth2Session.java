package com.hellokoding.springboot.oauth2;

public interface OAuth2Session {
    void put(String key, Object value);
    Object get(String key);
}
