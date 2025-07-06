package com.holberton_portfolio_project.BonAppEatIt.configuration;

import com.holberton_portfolio_project.BonAppEatIt.domain.PasswordPolicy;
import lombok.Getter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@Getter
public class PasswordPolicyConfig {

    // Needs mapping with @Qualifier to be used because several PasswordPolicyConfig beans can coexist within the class
    @Bean("standardPasswordPolicy")
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
