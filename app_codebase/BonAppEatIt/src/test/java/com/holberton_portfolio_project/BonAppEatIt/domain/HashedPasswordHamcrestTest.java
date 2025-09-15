package com.holberton_portfolio_project.BonAppEatIt.domain;

import org.junit.jupiter.api.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

public class HashedPasswordHamcrestTest {
    @Test
    void shouldReturnHashedPasswordObject() {
        var test1 = HashedPassword.fromBcryptHashString("$2a$12$R94jzkiBohNSUQM4vua3kOC5Z7k2gnuV5n8tedUnV5hkVNgpByWP6");
        var test2 = HashedPassword.fromBcryptHashString("$2a$12$R94jzkiBohNSUQM4vua3kOC5Z7k2gnuV5n8tedUnV5hkVNgpByWP6");

        assertThat(test1.getValue(), equalTo(test2.getValue()));
    }
}
