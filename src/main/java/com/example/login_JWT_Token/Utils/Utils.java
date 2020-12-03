package com.example.login_JWT_Token.Utils;

import org.springframework.stereotype.Component;
import javax.xml.bind.DatatypeConverter;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Component
public class Utils {

    public String md5(String password) throws NoSuchAlgorithmException {
        MessageDigest md5 = MessageDigest.getInstance("MD5");
        md5.update(password.getBytes());
        byte[] digest = md5.digest();
        return DatatypeConverter.printHexBinary(digest).toLowerCase();
    }

    private String passwordPattern = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z]).{8,10}$";

    public boolean validatePassword(String password){
        return Pattern.matches(passwordPattern, password);
    }


}

