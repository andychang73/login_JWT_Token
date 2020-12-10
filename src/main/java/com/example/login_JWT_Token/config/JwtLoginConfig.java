package com.example.login_JWT_Token.config;

import com.example.login_JWT_Token.aop.LogAspect;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

@Configuration
@EnableAspectJAutoProxy
public class JwtLoginConfig {

    @Bean
    public LogAspect logAspect(){
        return new LogAspect();
    }
}
