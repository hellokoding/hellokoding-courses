package com.hellokoding.account.service;

import com.hellokoding.account.model.User;

public interface UserService {
    void save(User user);

    User findByUsername(String username);
}
