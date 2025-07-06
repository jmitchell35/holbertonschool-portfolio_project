package com.holberton_portfolio_project.BonAppEatIt.service;

import com.holberton_portfolio_project.BonAppEatIt.domain.HashedPassword;
import com.holberton_portfolio_project.BonAppEatIt.domain.PasswordPolicy;
import com.holberton_portfolio_project.BonAppEatIt.dto.UserCreatedDTO;
import com.holberton_portfolio_project.BonAppEatIt.dto.UserCreationDTO;
import com.holberton_portfolio_project.BonAppEatIt.exceptions.UserAlreadyExistsException;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service
public class AuthService {
    private final UserService userService;
    private final PasswordValidationService passwordValidationService;
    private final PasswordPolicy standardPasswordPolicy;

    public AuthService(
            UserService userService,
            PasswordValidationService passwordValidationService,
            @Qualifier("standardPasswordPolicy") PasswordPolicy standardPasswordPolicy
            ) {
        this.userService = userService;
        this.passwordValidationService = passwordValidationService;
        this.standardPasswordPolicy = standardPasswordPolicy;
    }

    public UserCreatedDTO createUser(UserCreationDTO dto) {

        // Check email is unique
        if (userService.emailAlreadyExists(dto.getEmail())) {
            throw new UserAlreadyExistsException("Email is already registered.");
        }

        // Check password meets business rules
        passwordValidationService.validatePassword(dto.getPassword(), standardPasswordPolicy);

        // hash password
        HashedPassword hashedPassword = new HashedPassword(encodePassword(dto.getPassword()));

        // create user entity from DTO
        return userService.persistUser(dto.getEmail(), hashedPassword);
    }
}
