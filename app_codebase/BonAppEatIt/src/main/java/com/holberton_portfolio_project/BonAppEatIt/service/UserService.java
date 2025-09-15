package com.holberton_portfolio_project.BonAppEatIt.service;

import com.holberton_portfolio_project.BonAppEatIt.domain.HashedPassword;
import com.holberton_portfolio_project.BonAppEatIt.dto.UserCreatedDTO;
import com.holberton_portfolio_project.BonAppEatIt.models.Role;
import com.holberton_portfolio_project.BonAppEatIt.models.User;
import com.holberton_portfolio_project.BonAppEatIt.repository.RoleRepository;
import com.holberton_portfolio_project.BonAppEatIt.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@Service
@AllArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;

    public UserCreatedDTO createUser(String email, String username,HashedPassword password) {
        Role defaultRole = roleRepository.findByRole("ROLE_USER")
                .orElseThrow(() -> new RuntimeException("Default role 'ROLE_USER' not found in the database"));

        User newUser = User.builder()
                .email(email)
                .username(username)
                .password(password.getValue())
                .passwordUpdatedAt(LocalDateTime.now())
                .roles(new HashSet<>(Set.of(defaultRole)))
                .isActive(true)
                .isVerified(false)
                .build();

        userRepository.save(newUser);

        return UserCreatedDTO.builder()
                .email(email)
                .username(username)
                .build();
    }

    // package private (no modifier instead of private)
    boolean emailAlreadyExists(String email) {
        return userRepository.existsByEmail(email);
    }

    boolean usernameAlreadyExists(String username) {
        return userRepository.existsByUsername(username);
    }

    Optional<String> getUsername(String email) {
        return userRepository.findByEmail(email)
                .map(User::getUsername);
    }
}
