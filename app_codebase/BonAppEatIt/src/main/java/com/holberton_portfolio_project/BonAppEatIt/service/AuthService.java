package com.holberton_portfolio_project.BonAppEatIt.service;

import com.holberton_portfolio_project.BonAppEatIt.dto.UserCreatedDTO;
import com.holberton_portfolio_project.BonAppEatIt.dto.UserCreationDTO;
import com.holberton_portfolio_project.BonAppEatIt.exceptions.UserAlreadyExistsException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Service
public class AuthService {
    private final UserService userService;

    public UserCreatedDTO createUser(UserCreationDTO dto) {
        if (userService.emailAlreadyExists(dto.getEmail())) {
            throw new UserAlreadyExistsException("Email is already registered.");
        }
        // check password strength (specific method)
        // hash password
        // propagate exceptions to controller level for proper HTTP response
        // create user entity from DTO
        // write to the db and sync
        // create UserCreatedDTO from entity

        return userService.createUser(dto);
    }
}
