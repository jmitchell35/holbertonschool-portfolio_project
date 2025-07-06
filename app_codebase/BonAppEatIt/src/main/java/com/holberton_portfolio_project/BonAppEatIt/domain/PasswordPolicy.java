package com.holberton_portfolio_project.BonAppEatIt.domain;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class PasswordPolicy {
    private int minLength;
    private boolean requireUppercase;
    private boolean requireLowercase;
    private boolean requireNumbers;
    private boolean requireSpecialChars;
}
