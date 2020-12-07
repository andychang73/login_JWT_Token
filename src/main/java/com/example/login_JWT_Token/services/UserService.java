package com.example.login_JWT_Token.services;

import com.example.login_JWT_Token.entities.Role;

import java.util.List;

public interface UserService {

    void insert(String name, String password, List<Role> roles);

//    User findUserByUsername(String username);
}
