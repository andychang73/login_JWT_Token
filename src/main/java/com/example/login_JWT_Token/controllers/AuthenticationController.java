package com.example.login_JWT_Token.controllers;

import com.example.login_JWT_Token.entities.User;
import com.example.login_JWT_Token.services.impl.AuthenticationServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/authenticate")
public class AuthenticationController {

    @Autowired
    AuthenticationServiceImpl authenticationService;

//    @PostMapping("/login")
//    public String login(@RequestBody User user){
//
//    }
}
