package com.holberton_portfolio_project.BonAppEatIt.utils.validation;

import com.holberton_portfolio_project.BonAppEatIt.dto.UserCreationDTO;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class PasswordsMatchValidator implements ConstraintValidator<PasswordsMatch, UserCreationDTO> {

    // ConstraintValidator interface expects initialize and isValid method are implemented
    @Override
    public void initialize(PasswordsMatch constraintAnnotation) {
        /*
        Called when validator is created.
        Generally empty, except if validator implements custom properties which extend message(), groups() and payload()
        Additional properties could be :
        boolean caseSensitive() default true;
        Allowing calling :
        @PasswordsMatch(caseSensitive = false)
         */
    }

    @Override
    public boolean isValid(UserCreationDTO dto, ConstraintValidatorContext context) {
        /*
        The context parameter is created and managed by the Bean Validation framework (Hibernate Validator)
        The context is then automatically passed on to the validator function.
        It can be used to :
        - disable the default message for constraint violation
        - add a custom message instead
        - attach errors to specific fields instead of class level
        - implement several validation messages (length, match...)

        Null values should be handled by @NotNull constraints on DTO
         */
        return dto.getPassword().equals(dto.getPasswordConfirmation());
    }
}