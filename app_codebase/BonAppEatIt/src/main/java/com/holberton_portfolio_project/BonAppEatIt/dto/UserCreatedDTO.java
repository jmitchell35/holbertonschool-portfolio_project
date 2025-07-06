package com.holberton_portfolio_project.BonAppEatIt.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
@AllArgsConstructor
public class UserCreatedDTO {
    private String email;
}
