package com.holberton_portfolio_project.BonAppEatIt.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;

import java.util.UUID;

@Getter
public class RecipeIngredientInputDTO {
    @NotNull(message = "Ingredient ID is required")
    private UUID ingredientId;

    @NotNull(message = "Unit ID is required")
    private UUID unitId;

    @Min(value = 1, message = "Quantity must be at least 1")
    private short quantity;
}
