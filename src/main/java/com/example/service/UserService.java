package com.example.service;

import com.example.model.User;

import java.util.List;
import java.util.Optional;

public interface UserService {

    List<User> findAll();
    User findById(long id);
    User save(User user);
    void removeById(long id);
    void resetById(Long id);
    List<User> findAllByIsDeletedTrue();
    List<User> findAllByIsDeletedFalse();
    Optional<User> findByUsername(String username);
}
