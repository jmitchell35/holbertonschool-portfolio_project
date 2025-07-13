package com.holberton_portfolio_project.BonAppEatIt.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;

@Getter
public class InstructionInputDTO {
    @NotBlank(message = "Instructions cannot be empty")
    private String text;

    @NotNull(message = "The step number cannot be null")
    /*
    Integer because a primitive int has a default value of 0
    However, frontend js consider all numbers as 64-bit floats.
    PostgreSQL also stores Integers efficiently if inferior to a max Short value.
     */
    private Integer stepNumber;
}
