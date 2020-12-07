package com.example.login_JWT_Token.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Role {
    private String role;

    @Override
    public String toString(){
        return this.role;
    }
}
