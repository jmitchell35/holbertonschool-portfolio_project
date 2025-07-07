package com.holberton_portfolio_project.BonAppEatIt.service;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class PasswordEncodingService implements PasswordEncoder {

    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    public  PasswordEncodingService() {
        this.bCryptPasswordEncoder = new BCryptPasswordEncoder(12);
    }

    public String hashPassword(String password) {
        return bCryptPasswordEncoder.encode(password);
    }

    public boolean comparePasswords(String rawPassword, String encodedPassword) {
        return bCryptPasswordEncoder.matches(rawPassword, encodedPassword);
    }

    @Override
    public boolean upgradeEncoding(String encodedPassword) {
        return PasswordEncoder.super.upgradeEncoding(encodedPassword);
    }

    @Override
    public boolean matches(CharSequence rawPassword, String encodedPassword) {
        return comparePasswords(rawPassword.toString(), encodedPassword);
    }

    @Override
    public String encode(CharSequence rawPassword) {
        return hashPassword(rawPassword.toString());
    }
}
