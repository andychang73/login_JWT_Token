package com.example.login_JWT_Token.controllers;

import com.example.login_JWT_Token.Utils.JwtUtils;
import com.example.login_JWT_Token.model.AuthenticationRequest;
import com.example.login_JWT_Token.model.AuthenticationResponse;
import com.example.login_JWT_Token.services.impl.UserServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/authenticate")
public class AuthenticationController {

    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    JwtUtils jwtUtils;

    @Autowired
    UserServiceImpl userService;

    @PostMapping("/login")
    public ResponseEntity<AuthenticationResponse> login(@RequestBody AuthenticationRequest request){
        try{
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(request.getUsername(),request.getPassword())
            );
        }catch(BadCredentialsException e){
            log.error("[login] failed cause:", e);
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }

        final UserDetails user = userService.loadUserByUsername(request.getUsername());
        final String jwt = jwtUtils.generateToken(user.getUsername());
        log.info("[login] successful user details: {} token: {}", user, jwt);
        AuthenticationResponse response = new AuthenticationResponse(jwt);
        return ResponseEntity.ok(response);
    }
}
