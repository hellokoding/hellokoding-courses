package com.hellokoding.tutorials.service;

import com.hellokoding.tutorials.model.User;

public interface UserService {
    void save(User user);

    User findByUsername(String username);
}
