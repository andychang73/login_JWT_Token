package com.example.login_JWT_Token.constant;

public class SecurityConstant {
    public static final String SECRET_KEY = "fjeiwofjdkfuekwnfkdowkfhjeikfnaqoriejdks";
    public static final long JWT_VALIDITY = 5 * 60 * 60 * 1000;
    public static final String TOKEN_PREFIX = "Bearer ";
    public static final String HEADER = "Authorization";
}
