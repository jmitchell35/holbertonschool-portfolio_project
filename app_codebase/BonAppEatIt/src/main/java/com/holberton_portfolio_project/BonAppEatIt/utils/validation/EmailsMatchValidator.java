package com.holberton_portfolio_project.BonAppEatIt.utils.validation;

import com.holberton_portfolio_project.BonAppEatIt.dto.UserCreationDTO;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class EmailsMatchValidator implements ConstraintValidator<EmailsMatch, UserCreationDTO> {
    @Override
    public void initialize(EmailsMatch constraintAnnotation) {}

    @Override
    public boolean isValid(UserCreationDTO dto, ConstraintValidatorContext context) {
        return dto.getEmail().equals(dto.getEmailConfirmation());
    }
}
