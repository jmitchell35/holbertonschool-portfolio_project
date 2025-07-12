package com.holberton_portfolio_project.BonAppEatIt.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.util.UUID;

@Getter
@Builder
@AllArgsConstructor
public class IngredientDTO {
    private UUID id;
    private String ingredientSingular;
    private String ingredientPlural;
}
