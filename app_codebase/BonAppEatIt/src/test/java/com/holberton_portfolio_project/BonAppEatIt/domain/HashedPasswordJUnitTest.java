package com.holberton_portfolio_project.BonAppEatIt.domain;

import org.junit.jupiter.api.Assertions;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertInstanceOf;



public class HashedPasswordJUnitTest {
    @Test
    void shouldReturnHashedPasswordObject() {
        var test = HashedPassword.fromBcryptHashString("$2a$12$R94jzkiBohNSUQM4vua3kOC5Z7k2gnuV5n8tedUnV5hkVNgpByWP6");

        assertInstanceOf(HashedPassword.class, test);
    }

    @Test
    void shoudNotReturnHashedPassword() {
        Assertions.assertThrows(IllegalArgumentException.class, () ->
                HashedPassword.fromBcryptHashString("P@ssword12345"));
    }
}