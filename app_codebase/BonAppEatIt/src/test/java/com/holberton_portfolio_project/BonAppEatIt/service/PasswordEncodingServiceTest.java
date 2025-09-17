package com.holberton_portfolio_project.BonAppEatIt.service;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.*;

public class PasswordEncodingServiceTest {
    private final PasswordEncodingService passwordEncodingService =  new PasswordEncodingService();
    private final String password = "P@ssword12345";

    @Test
    public void shouldEncodePassword() {

        String encodedPassword = passwordEncodingService.hashPassword(password);

        assertThat(encodedPassword)
                .isNotNull()
                .isInstanceOf(String.class)
                .startsWith("$2a$12$");

        assertThat(passwordEncodingService.comparePasswords(password, encodedPassword))
                .isTrue();
    }

    @Test
    public void shouldImplementPasswordEncoderInterface() {
        String encoded = passwordEncodingService.encode(password);

        assertThat(encoded).startsWith("$2a$12$");
        assertThat(passwordEncodingService.matches(password, encoded)).isTrue();
    }

    @Test
    public void shouldOutputTwoDifferentHashes() {
        String hash1 = passwordEncodingService.hashPassword(password);
        String hash2= passwordEncodingService.hashPassword(password);

        assertThat(hash1).isNotEqualTo(hash2);
    }

    @Test
    public void shouldRejectWrongPassword() {
        String encoded = passwordEncodingService.hashPassword(password);

        assertThat(passwordEncodingService.comparePasswords("wrongpassword", encoded))
                .isFalse();
    }

}
