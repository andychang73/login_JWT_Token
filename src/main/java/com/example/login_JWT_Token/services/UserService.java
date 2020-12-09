package com.example.login_JWT_Token.services;

import com.example.login_JWT_Token.entities.Role;

import java.util.Set;

public interface UserService {

    void insert(String name, String password, Set<Role> roles);

//    User findUserByUsername(String username);
}
