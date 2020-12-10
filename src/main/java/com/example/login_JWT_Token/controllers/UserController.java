package com.example.login_JWT_Token.controllers;

import com.example.login_JWT_Token.Utils.Utils;
import com.example.login_JWT_Token.entities.User;
import com.example.login_JWT_Token.services.impl.UserServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import javax.annotation.security.RolesAllowed;
import javax.validation.Valid;


@Slf4j
@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    UserServiceImpl userServiceImpl;

    @Autowired
    Utils utils;

    @Autowired
    PasswordEncoder passwordEncoder;

    @PostMapping("/create")
    public void insertUser(@RequestBody @Valid User user) {
        if(!utils.validatePassword(user.getPassword())){
            return;
        }
        String password = passwordEncoder.encode(user.getPassword());
        userServiceImpl.insert(user.getUsername(), password, user.getRoles());
    }

    @RolesAllowed("ROLE_USER")
    @GetMapping("/user")
    public String userOnly(){
        return "user";
    }

    @RolesAllowed("ROLE_ADMIN")
    @GetMapping("/admin")
    public String adminOnly(){
        return "admin";
    }

    @RolesAllowed({"ROLE_USER", "ROLE_ADMIN"})
    @GetMapping("/both")
    public String both(){
        return "user & admin";
    }
}
