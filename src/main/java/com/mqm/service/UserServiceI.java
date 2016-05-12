package com.mqm.service;

import java.util.List;

import com.mqm.entity.User;

public interface UserServiceI{
	
	User getCurrentUser();

    void createUser(String username, String email, String password);

    List<User> getAllUsers();

    User getUser(Long userId);

    void deleteUser(Long userId);

    void updateUser(User user);
    
    User findUser(String userName);
}