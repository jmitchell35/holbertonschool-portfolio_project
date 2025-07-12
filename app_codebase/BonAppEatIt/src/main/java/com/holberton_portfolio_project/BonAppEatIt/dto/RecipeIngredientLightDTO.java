package com.holberton_portfolio_project.BonAppEatIt.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
@AllArgsConstructor
public class RecipeIngredientLightDTO {
    private IngredientDTO ingredient;
    private String unit;
    private short quantity;
}
