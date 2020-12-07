package com.example.login_JWT_Token.services.impl;

import com.example.login_JWT_Token.Utils.Utils;
import com.example.login_JWT_Token.dao.UserMapper;
import com.example.login_JWT_Token.entities.Role;
import com.example.login_JWT_Token.entities.User;
import com.example.login_JWT_Token.services.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import static java.util.Collections.emptyList;


@Slf4j
@Service
public class UserServiceImpl implements UserService, UserDetailsService {

    @Autowired
    UserMapper userMapper;

    @Autowired
    Utils utils;

    @Override
    public void insert(String name, String password, List<Role> roles) {
        List<GrantedAuthority> authoritiesList = roles.stream().map(r -> new SimpleGrantedAuthority(r.getRole())).collect(Collectors.toList());
        userMapper.insert(name, password, listToString(roles), listToString(authoritiesList) );
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userMapper.findUserByUsername(username);
        log.info("user:{}", user);
        if(Objects.isNull(user)){
            throw new UsernameNotFoundException(username);
        }
        return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(), emptyList());
    }

    private <T> String listToString(List<T> list){
        StringBuilder sb = new StringBuilder();
        for(int i = 0; i < list.size(); i++){
            if(i < list.size()-1){
                sb.append(list.get(i)).append(",");
            }else{
                sb.append(list.get(i));
            }
        }
        return sb.toString();
    }

    public static void main(String[] args){

    }
}
