package com.innopo.Services;

import org.springframework.security.crypto.password.PasswordEncoder;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;


public class CustomPasswordEncoder implements PasswordEncoder {


    public static String md5(CharSequence password) {
        String result = null;
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            byte[] array = password.toString().getBytes();
            byte[] arraySecond = md.digest(array);
            result = new String(arraySecond);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return result;
    }


    @Override
    public String encode(CharSequence rawPassword) {
        String result = md5(rawPassword) + "mySalt";
        StringBuilder a = new StringBuilder(result).reverse();
        result = a.toString();
        result = md5(result);
        return result;
    }

    @Override
    public boolean matches(CharSequence rawPassword, String encodedPassword) {
        return encode(rawPassword).equals(encodedPassword);
    }
}
