package com.holberton_portfolio_project.BonAppEatIt.domain;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class PasswordPolicy {
    private int minLength;
    private boolean requiredUppercase;
    private boolean requiredLowercase;
    private boolean requiredDigits;
    private boolean requiredSpecialChars;
}
