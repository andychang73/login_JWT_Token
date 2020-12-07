package com.example.login_JWT_Token.model;

import lombok.Data;

@Data
public class AuthenticationRequest {
    private String username;
    private String password;
}
