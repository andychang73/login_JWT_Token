package com.example.login_JWT_Token.services.impl;

import com.example.login_JWT_Token.Utils.Utils;
import com.example.login_JWT_Token.entities.User;
import com.example.login_JWT_Token.services.AuthenticationService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.security.NoSuchAlgorithmException;
import java.util.Objects;

@Slf4j
@Service
public class AuthenticationServiceImpl implements AuthenticationService {

    @Autowired
    Utils utils;

    @Autowired
    UserServiceImpl userService;

    @Override
    public boolean authenticate(User user) throws NoSuchAlgorithmException {
        User dbUser = userService.loadUserByUsername(user.getName());
        if(Objects.isNull(dbUser)){
            return false;
        }
        String hashedPassword = utils.md5(user.getPassword());
        if(hashedPassword.equals(dbUser.getPassword())){
            return true;
        }
        return false;
    }
}
