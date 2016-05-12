package com.mqm.base.dao;

import java.util.List;

import com.mqm.entity.User;

public interface UserDaoI {

	User getUser(Long userId);

    User findUser(String username);

    void createUser(User user);

    List<User> getAllUsers();

    void deleteUser(Long userId);

    void updateUser(User user);
}
