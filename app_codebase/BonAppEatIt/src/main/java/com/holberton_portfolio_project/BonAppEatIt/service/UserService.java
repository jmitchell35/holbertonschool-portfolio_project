package com.holberton_portfolio_project.BonAppEatIt.service;

import com.holberton_portfolio_project.BonAppEatIt.dto.UserCreatedDTO;
import com.holberton_portfolio_project.BonAppEatIt.dto.UserCreationDTO;
import com.holberton_portfolio_project.BonAppEatIt.dto.UserLoginDTO;
import com.holberton_portfolio_project.BonAppEatIt.exceptions.UserAlreadyExistsException;
import com.holberton_portfolio_project.BonAppEatIt.repository.UserRepository;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;


@Service
@AllArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    public UserCreatedDTO createUser(UserCreationDTO dto) {
        if (emailAlreadyExists(dto.getEmail())) {
            throw new UserAlreadyExistsException("Email is already registered.");
        }
        // check password strength (specific method)
        // hash password
        // propagate exceptions to controller level for proper HTTP response
        // create user entity from DTO
        // write to the db and sync
        // create UserCreatedDTO from entity
    }

    // package private (no modifier instead of private)
    boolean emailAlreadyExists(String email) {
        return userRepository.existsByEmail(email);
    }

    private boolean isStrongPassword(String password) {

    }

}
