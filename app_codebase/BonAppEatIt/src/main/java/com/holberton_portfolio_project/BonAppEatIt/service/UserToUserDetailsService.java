package com.holberton_portfolio_project.BonAppEatIt.service;

import com.holberton_portfolio_project.BonAppEatIt.models.User;
import com.holberton_portfolio_project.BonAppEatIt.repository.UserRepository;
import com.holberton_portfolio_project.BonAppEatIt.security.UserToUserDetails;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserToUserDetailsService implements UserDetailsService {
    private final UserRepository userRepository;

    public UserToUserDetailsService (UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public UserDetails loadUserByUsername(String email) {
        Optional<User> user = userRepository.findByEmail(email);

        if (user.isPresent()) {
            return new UserToUserDetails(user.get());
        } else {
            throw new UsernameNotFoundException("User " + email + " not found");
        }
    }
}
