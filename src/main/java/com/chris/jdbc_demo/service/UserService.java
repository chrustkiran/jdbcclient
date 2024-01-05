package com.chris.jdbc_demo.service;

import com.chris.jdbc_demo.model.User;

import java.util.Collection;

public interface UserService {
    Collection<User> findAll();
    void create(User user);
    void update(User user, String id);
    void delete(String id);
}
