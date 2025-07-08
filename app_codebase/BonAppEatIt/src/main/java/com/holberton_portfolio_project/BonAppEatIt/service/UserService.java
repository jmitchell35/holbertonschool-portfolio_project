package com.holberton_portfolio_project.BonAppEatIt.service;

import com.holberton_portfolio_project.BonAppEatIt.domain.HashedPassword;
import com.holberton_portfolio_project.BonAppEatIt.dto.UserCreatedDTO;
import com.holberton_portfolio_project.BonAppEatIt.models.User;
import com.holberton_portfolio_project.BonAppEatIt.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@AllArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    public UserCreatedDTO createUser(String email, HashedPassword password) {
        User persistedUser = User.builder()
                .email(email)
                .password(String.valueOf(password))
                .passwordUpdatedAt(LocalDateTime.now())
                .build();

        userRepository.save(persistedUser);

        return UserCreatedDTO.builder()
                .email(email)
                .build();
    }

    // package private (no modifier instead of private)
    boolean emailAlreadyExists(String email) {
        return userRepository.existsByEmail(email);
    }
}
