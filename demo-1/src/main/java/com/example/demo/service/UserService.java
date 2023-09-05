package com.example.demo.service;

import java.util.List;

import com.example.demo.model.User;

public interface UserService {

    User findByUserId(Long id);
    List<User> findAll();
    User createUser(User user);
    User updateUser(User user);
    void deleteUser(Long userId);

}
