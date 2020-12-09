package com.example.login_JWT_Token.handler;

import com.example.login_JWT_Token.entities.Role;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.type.BaseTypeHandler;
import org.apache.ibatis.type.JdbcType;
import org.apache.ibatis.type.MappedJdbcTypes;
import org.apache.ibatis.type.MappedTypes;
import org.springframework.util.StringUtils;


import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

@Slf4j
@MappedTypes({Set.class})
@MappedJdbcTypes({JdbcType.VARCHAR})
public class StringToListTypeHandler extends BaseTypeHandler<Set<String>> {
    @Override
    public void setNonNullParameter(PreparedStatement preparedStatement, int i, Set<String> strings, JdbcType jdbcType) throws SQLException {
        if(Objects.isNull(jdbcType)){
            preparedStatement.setString(i, StringUtils.collectionToCommaDelimitedString(strings));
        }else{
            preparedStatement.setObject(i, strings, jdbcType.TYPE_CODE);
        }
    }

    @Override
    public Set<String> getNullableResult(ResultSet resultSet, String s) throws SQLException {
        String str = resultSet.getString(s);
        Set<String> roles = new HashSet<>();
        String[] rolesArray = str.split(",");
        for(String r: rolesArray){
            roles.add(r);
        }
        return roles;
    }

    @Override
    public Set<String> getNullableResult(ResultSet resultSet, int i) throws SQLException {
        String str = resultSet.getString(i);
        Set<String> roles = new HashSet<>();
        String[] rolesArray = str.split(",");
        for(String r: rolesArray){
            roles.add(r);
        }
        return roles;
    }

    @Override
    public Set<String> getNullableResult(CallableStatement callableStatement, int i) throws SQLException {
        String str = callableStatement.getString(i);
        Set<String> roles = new HashSet<>();
        String[] rolesArray = str.split(",");
        for(String r: rolesArray){
            roles.add(r);
        }
        return roles;
    }
}
