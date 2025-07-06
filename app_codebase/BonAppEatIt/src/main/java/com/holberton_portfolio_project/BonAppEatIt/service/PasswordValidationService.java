package com.holberton_portfolio_project.BonAppEatIt.service;

import com.holberton_portfolio_project.BonAppEatIt.domain.PasswordPolicy;
import com.holberton_portfolio_project.BonAppEatIt.exceptions.WeakPasswordException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Service
public class PasswordValidationService {

    public void validatePassword(String password, PasswordPolicy policy) {
        if (password.length() < policy.getMinLength()) {
            throw new WeakPasswordException("Password must be at least " + policy.getMinLength() + " characters long");
        }
    }
}
