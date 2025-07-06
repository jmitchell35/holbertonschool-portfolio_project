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

        if (policy.isRequiredUppercase() && !hasUpperCaseCharacter(password)) {
            throw new WeakPasswordException("Password must contain at least one uppercase character");
        }

        if (policy.isRequiredLowercase() && !hasLowerCaseCharacter(password)) {
            throw new WeakPasswordException("Password must contain at least one lowercase character");
        }

        if (policy.isRequiredDigits() && !hasDigit(password)) {
            throw new WeakPasswordException("Password must contain at least one digit");
        }

        if (policy.isRequiredSpecialChars() && !hasSpecialChar(password)) {
            throw new WeakPasswordException("Password must contain at least one special character");
        }
    }

    private boolean hasUpperCaseCharacter(String password) {
        return password.matches(".*[A-Z].*");
    }

    private boolean hasLowerCaseCharacter(String password) {
        return password.matches(".*[a-z].*");
    }

    private boolean hasDigit(String password) {
        return password.matches(".*\\d.*");
    }

    private boolean hasSpecialChar(String password) {
        return password.matches(".*[!@#$%^&*()_+\\-=\\[\\]{},.<>?].*");
    }
}
