package com.holberton_portfolio_project.BonAppEatIt.domain;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class HashedPasswordAssertJTest {
    @Test
    void shouldReturnHashedPasswordObject() {
        var test = HashedPassword.fromBcryptHashString("$2a$12$R94jzkiBohNSUQM4vua3kOC5Z7k2gnuV5n8tedUnV5hkVNgpByWP6");

        assertThat(test.getValue())
                .startsWith("$2a$12$")
                .isEqualTo("$2a$12$R94jzkiBohNSUQM4vua3kOC5Z7k2gnuV5n8tedUnV5hkVNgpByWP6");
    }
}
