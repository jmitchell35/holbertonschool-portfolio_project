package com.holberton_portfolio_project.BonAppEatIt.utils;

// Lombok boilerplate methods
import lombok.Getter;


@Getter
public class HashedPassword {

    private final String value;

    // Private constructor for control : can only be accessed through static factory method
    private HashedPassword(String hashedValue) {
        if (!isValidBcryptHash(hashedValue)) {
            throw new IllegalArgumentException("Invalid password hash format");
        }
        // set only if passes format validation
        this.value = hashedValue;
    }

    // Static factory method : calls constructor, leaves no choice on the way to build the object as valid hash
    public static HashedPassword fromBcryptHashString(String hashedValue) {
        return new HashedPassword(hashedValue);
    }

    // Checks that the hash is a valid Bcrypt hash with pattern
    private boolean isValidBcryptHash(String hash) {
        return hash != null && hash.matches("^\\$2[ayb]\\$.{56}$");
    }

}

