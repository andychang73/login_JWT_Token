package com.example.login_JWT_Token.services;

import com.example.login_JWT_Token.entities.User;

import java.util.List;

public interface UserService {

    void insert(String name, String password, List<String> roles);

//    User findUserByUsername(String username);
}
