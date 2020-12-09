package com.example.login_JWT_Token.services.impl;

import com.baomidou.mybatisplus.core.toolkit.CollectionUtils;
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

import java.util.*;
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
    public void insert(String name, String password, Set<Role> roles) {
        Set<GrantedAuthority> authoritiesList = roles.stream().map(r -> new SimpleGrantedAuthority(r.getRole())).collect(Collectors.toSet());
        userMapper.insert(name, password, setToString(roles), setToString(authoritiesList) );
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        try{

            User user = userMapper.findUserByUsername(username);
            log.info("user: {}", user);
            if(Objects.isNull(user)){
                throw new UsernameNotFoundException(username);
            }
            return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(), user.getAuthorities());
        }catch(Exception e){
            log.error("Exception: ", e);
        }
        return null;
    }

    private <T> String setToString(Set<T> set){
        StringBuilder sb = new StringBuilder();
        Iterator<T> iterator = set.iterator();
        while(iterator.hasNext()){
            if(sb.length() > 0){
                sb.append(",");
            }
            sb.append(iterator.next());
        }
        return sb.toString();
    }

    public static void main(String[] args){
        Set<String> set = new HashSet<>();
        set.add("a");
        set.add("b");
        set.add("c");

        StringBuilder sb = new StringBuilder();
        Iterator<String> iterator = set.iterator();
        while(iterator.hasNext()){
            if(sb.length() > 0){
                sb.append(",");
            }
            sb.append(iterator.next());
        }
        System.out.println(sb.toString());
    }
}
