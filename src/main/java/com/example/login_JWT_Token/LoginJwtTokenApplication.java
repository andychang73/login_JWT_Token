package com.example.login_JWT_Token;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.example.login_JWT_Token")
public class LoginJwtTokenApplication {

	public static void main(String[] args) {
		SpringApplication.run(LoginJwtTokenApplication.class, args);
	}

}
