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
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

@Slf4j
@MappedTypes({List.class})
@MappedJdbcTypes({JdbcType.VARCHAR})
public class StringToListTypeHandler extends BaseTypeHandler<List<String>> {
    @Override
    public void setNonNullParameter(PreparedStatement preparedStatement, int i, List<String> strings, JdbcType jdbcType) throws SQLException {
        if(Objects.isNull(jdbcType)){
            preparedStatement.setString(i, StringUtils.collectionToCommaDelimitedString(strings));
        }else{
            preparedStatement.setObject(i, strings, jdbcType.TYPE_CODE);
        }
    }

    @Override
    public List<String> getNullableResult(ResultSet resultSet, String s) throws SQLException {
        String str = resultSet.getString(s);
        List<String> roles = new ArrayList<>();
        String[] rolesArray = str.split(",");
        for(String r: rolesArray){
            roles.add(r);
        }
        return roles;
    }

    @Override
    public List<String> getNullableResult(ResultSet resultSet, int i) throws SQLException {
        String str = resultSet.getString(i);
        List<String> roles = new ArrayList<>();
        String[] rolesArray = str.split(",");
        for(String r: rolesArray){
            roles.add(r);
        }
        return roles;
    }

    @Override
    public List<String> getNullableResult(CallableStatement callableStatement, int i) throws SQLException {
        String str = callableStatement.getString(i);
        List<String> roles = new ArrayList<>();
        String[] rolesArray = str.split(",");
        for(String r: rolesArray){
            roles.add(r);
        }
        return roles;
    }
}
