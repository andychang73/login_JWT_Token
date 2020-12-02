package com.example.login_JWT_Token.services;

import com.example.login_JWT_Token.entities.User;

import java.security.NoSuchAlgorithmException;

public interface AuthenticationService {

    boolean authenticate(User user) throws NoSuchAlgorithmException;
}

