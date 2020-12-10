package com.example.login_JWT_Token.handler;

import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.type.BaseTypeHandler;
import org.apache.ibatis.type.JdbcType;
import org.apache.ibatis.type.MappedJdbcTypes;
import org.apache.ibatis.type.MappedTypes;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.util.StringUtils;


import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Slf4j
@MappedTypes({Set.class})
@MappedJdbcTypes({JdbcType.VARCHAR})
public class StringToGrantedAuthorityHandler extends BaseTypeHandler<Set<GrantedAuthority>> {
    @Override
    public void setNonNullParameter(PreparedStatement preparedStatement, int i, Set<GrantedAuthority> grantedAuthorities, JdbcType jdbcType) throws SQLException {
        if(Objects.isNull(jdbcType)){
            preparedStatement.setString(i, StringUtils.collectionToCommaDelimitedString(grantedAuthorities));
        }else{
            preparedStatement.setObject(i, grantedAuthorities, jdbcType.TYPE_CODE);
        }
    }

    @Override
    public Set<GrantedAuthority> getNullableResult(ResultSet resultSet, String s) throws SQLException {
        String str = resultSet.getString(s);
        Set<GrantedAuthority> set = new HashSet<>();
        String[] aus = str.split(",");
        for(String i: aus){
            set.add(new SimpleGrantedAuthority(i));
        }
        return set;
    }

    @Override
    public Set<GrantedAuthority> getNullableResult(ResultSet resultSet, int i) throws SQLException {
        String str = resultSet.getString(i);
        Set<GrantedAuthority> set = new HashSet<>();
        String[] aus = str.split(",");
        for(String s: aus){
            set.add(new SimpleGrantedAuthority(s));
        }
        return set;
    }

    @Override
    public Set<GrantedAuthority> getNullableResult(CallableStatement callableStatement, int i) throws SQLException {
        String str = callableStatement.getString(i);
        Set<GrantedAuthority> set = new HashSet<>();
        String[] aus = str.split(",");
        for(String s: aus){
            set.add(new SimpleGrantedAuthority(s));
        }
        return set;
    }
}
