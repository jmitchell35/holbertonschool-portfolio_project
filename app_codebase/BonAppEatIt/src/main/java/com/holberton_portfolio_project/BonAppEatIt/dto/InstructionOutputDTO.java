package com.holberton_portfolio_project.BonAppEatIt.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
@AllArgsConstructor
public class InstructionOutputDTO {
    private String text;
    private int stepNumber;
}