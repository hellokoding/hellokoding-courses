package com.hellokoding.tutorials.service;

public interface SecurityService {
    boolean isAuthenticated();
    void autoLogin(String username, String password);
}
