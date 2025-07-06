package com.holberton_portfolio_project.BonAppEatIt.service;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class PasswordEncodingService {
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
}
