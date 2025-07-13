package com.holberton_portfolio_project.BonAppEatIt.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
@AllArgsConstructor
public class RecipeIngredientOutputDTO {
    private IngredientDTO ingredient;
    private UnitDTO unit;
    private short quantity;
}
