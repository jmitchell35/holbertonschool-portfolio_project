package com.holberton_portfolio_project.BonAppEatIt.dto;

import com.holberton_portfolio_project.BonAppEatIt.utils.validation.EmailsMatch;
import com.holberton_portfolio_project.BonAppEatIt.utils.validation.PasswordsMatch;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

@AllArgsConstructor
@Getter @Setter
@PasswordsMatch
@EmailsMatch
public class UserCreationDTO {
    @Email(message = "Please provide a valid email address")
    @NotBlank(message = "Email is required")
    private String email;

    @Email(message = "Please provide a valid email address")
    @NotBlank(message = "Email confirmation is required")
    private String emailConfirmation;

    @NotBlank(message = "Password is required")
    @Length(min = 12, max = 255, message = "Password should be at least 12 characters long")
    private String password;

    @NotBlank(message = "Password confirmation is required")
    @Length(min = 12, max = 255, message = "Password should be at least 12 characters long")
    private String passwordConfirmation;
}
