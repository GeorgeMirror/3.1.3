package com.gubkina.springrest.service;

import com.gubkina.springrest.entity.User;

import java.util.List;

public interface UserService {

    List<User> getAllUsers();
    User createNewUser(User user);
    User getUserById(long id);
    User updateUser(User user);
    void deleteById(long id);
}
