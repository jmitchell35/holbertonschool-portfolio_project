package com.holberton_portfolio_project.BonAppEatIt.service;

import com.holberton_portfolio_project.BonAppEatIt.domain.PasswordPolicy;
import com.holberton_portfolio_project.BonAppEatIt.exceptions.WeakPasswordException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class PasswordValidationServiceTest {
    private final PasswordValidationService passwordValidationService = new PasswordValidationService();
    private final String validPassword = "P@ssword12345";
    private final String wrongLength = "P@ss1234";
    private final String noUpper = "p@ssword12345";
    private final String noLower = "P@SSWORD12345";
    private final String noDigit = "Superp@ssword";
    private final String noSpecialChars = "Password12345";
    private final PasswordPolicy policy = PasswordPolicy.builder()
            .minLength(12)
            .requiredUppercase(true)
            .requiredLowercase(true)
            .requiredDigits(true)
            .requiredSpecialChars(true)
            .build();

    @Test
    void shouldValidatePassword() {
        Assertions.assertDoesNotThrow(() -> passwordValidationService.validatePassword(validPassword, policy));
    }

    @Test
    void shouldThrowWeakPasswordExceptionLength() {
        WeakPasswordException exception = Assertions.assertThrows(WeakPasswordException.class,
                () -> passwordValidationService.validatePassword(wrongLength, policy));

        Assertions.assertEquals("Password must be at least " + policy.getMinLength() + " characters long",  exception.getMessage());
    }

    @Test
    void shouldThrowWeakPasswordExceptionUpper() {
        WeakPasswordException exception = Assertions.assertThrows(WeakPasswordException.class, () ->
            passwordValidationService.validatePassword(noUpper, policy));

        Assertions.assertEquals("Password must contain at least one uppercase character",  exception.getMessage());
    }

    @Test
    void shouldThrowWeakPasswordExceptionLower() {
        WeakPasswordException exception = Assertions.assertThrows(WeakPasswordException.class, () ->
            passwordValidationService.validatePassword(noLower, policy));

        Assertions.assertEquals("Password must contain at least one lowercase character",  exception.getMessage());
    }

    @Test
    void shouldThrowWeakPasswordExceptionDigit() {
        WeakPasswordException exception = Assertions.assertThrows(WeakPasswordException.class, () ->
            passwordValidationService.validatePassword(noDigit, policy));

        Assertions.assertEquals("Password must contain at least one digit",  exception.getMessage());
    }

    @Test
    void shouldThrowWeakPasswordExceptionSpecial() {
        WeakPasswordException exception = Assertions.assertThrows(WeakPasswordException.class, () ->
            passwordValidationService.validatePassword(noSpecialChars, policy));

        Assertions.assertEquals("Password must contain at least one special character",  exception.getMessage());
    }
}
