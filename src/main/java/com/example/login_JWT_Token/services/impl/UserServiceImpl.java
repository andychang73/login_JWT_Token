package com.example.login_JWT_Token.services.impl;

import com.example.login_JWT_Token.Utils.Utils;
import com.example.login_JWT_Token.dao.UserMapper;
import com.example.login_JWT_Token.entities.User;
import com.example.login_JWT_Token.model.UserVo;
import com.example.login_JWT_Token.services.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import java.util.Objects;

import static java.util.Collections.emptyList;


@Slf4j
@Service
public class UserServiceImpl implements UserService, UserDetailsService {

    @Autowired
    UserMapper userMapper;

    @Autowired
    Utils utils;

    @Override
    public void insert(String name, String password) {
        userMapper.insert(name, password);
    }

//    @Override
//    public User findUserByUsername(String username) {
//        return userMapper.findUserByUsername(username);
//    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userMapper.findUserByUsername(username);
        if(Objects.isNull(user)){
            throw new UsernameNotFoundException(username);
        }
        return new org.springframework.security.core.userdetails.User(user.getName(), user.getPassword(), emptyList());
    }
}
