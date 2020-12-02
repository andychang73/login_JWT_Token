package com.example.login_JWT_Token.controllers;

import com.example.login_JWT_Token.Utils.Utils;
import com.example.login_JWT_Token.entities.User;
import com.example.login_JWT_Token.services.impl.UserServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.security.NoSuchAlgorithmException;

@Slf4j
@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    UserServiceImpl userServiceImpl;

    @Autowired
    Utils utils;

    @PostMapping("/create")
    public void insertUser(@RequestBody @Valid User user) throws NoSuchAlgorithmException {
        if(!utils.validatePassword(user.getPassword())){
            return;
        }
        userServiceImpl.insert(user.getName(), utils.md5(user.getPassword()));
    }

    @PostMapping("/login")
    public String login(@RequestBody @Valid User user){
        return user.toString();
    }
}
