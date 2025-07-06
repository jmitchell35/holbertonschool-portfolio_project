package com.holberton_portfolio_project.BonAppEatIt.configuration;

import com.holberton_portfolio_project.BonAppEatIt.domain.PasswordPolicy;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class PasswordPolicyConfig {

    @Bean
    public PasswordPolicy standardPasswordPolicy() {
        return PasswordPolicy.builder()
                .minLength(12)
                .requireUppercase(true)
                .requireLowercase(true)
                .requireNumbers(true)
                .requireSpecialChars(true)
                .build();
    }
}
