package com.example.login_JWT_Token.dao;

import com.example.login_JWT_Token.entities.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;


@Mapper
@Repository
public interface UserMapper{

     void insert(@Param("username") String username, @Param("password") String password, @Param("roles") String roles,
                 @Param("authorities") String authorities);

     User findUserByUsername(@Param("username") String username);
}
