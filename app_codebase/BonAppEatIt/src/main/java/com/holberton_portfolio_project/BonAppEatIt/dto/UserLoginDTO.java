package com.holberton_portfolio_project.BonAppEatIt.dto;


import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserLoginDTO {
    @Email(message = "Please enter a valid email address")
    @NotEmpty(message = "Please enter the email address you registered with")
    private String email;

    @NotEmpty(message = "Please enter your password")
    private String password;
}
