package com.holberton_portfolio_project.BonAppEatIt.dto;

import com.holberton_portfolio_project.BonAppEatIt.models.Ingredient;
import com.holberton_portfolio_project.BonAppEatIt.models.Unit;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
@AllArgsConstructor
public class RecipeIngredientLightDTO {
    private Ingredient ingredient;
    private Unit unit;
    private short quantity;
}
