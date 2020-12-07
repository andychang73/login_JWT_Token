package com.example.login_JWT_Token.dao;

import com.example.login_JWT_Token.entities.Role;
import com.example.login_JWT_Token.entities.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface UserMapper{

     void insert(@Param("username") String username, @Param("password") String password, @Param("roles") String roles);

     User findUserByUsername(@Param("username") String username);
}
