package com.holberton_portfolio_project.BonAppEatIt.mappers;

import com.holberton_portfolio_project.BonAppEatIt.dto.IngredientDTO;
import com.holberton_portfolio_project.BonAppEatIt.dto.RecipeIngredientOutputDTO;
import com.holberton_portfolio_project.BonAppEatIt.dto.UnitDTO;
import com.holberton_portfolio_project.BonAppEatIt.models.RecipeIngredient;
import org.springframework.stereotype.Component;

import java.util.Set;
import java.util.stream.Collectors;

@Component
public class RecipeIngredientMapper {
    public RecipeIngredientOutputDTO toRecipeIngredientOutputDTO(RecipeIngredient recipeIngredient) {
        return RecipeIngredientOutputDTO.builder()
                .ingredient(IngredientDTO.builder()
                        .id(recipeIngredient.getIngredient().getId())
                        .ingredientSingular(recipeIngredient.getIngredient().getIngredientSingular())
                        .ingredientPlural(recipeIngredient.getIngredient().getIngredientPlural())
                        .build())
                .quantity(recipeIngredient.getQuantity())
                .unit(UnitDTO.builder()
                        .id(recipeIngredient.getUnit().getId())
                        .name(recipeIngredient.getUnit().getName())
                        .build())
                .build();
    }

    public Set<RecipeIngredientOutputDTO> toRecipeIngredientOutputDTO(Set<RecipeIngredient> recipeIngredients) {
        return recipeIngredients.stream()
                .map(this::toRecipeIngredientOutputDTO)
                .collect(Collectors.toSet());
    }

    public RecipeIngredientOutputDTO toRecipeIngredientInputDTO(RecipeIngredient recipeIngredient) {
        return RecipeIngredientOutputDTO.builder()
                .build();
    }

    public Set<RecipeIngredientOutputDTO> toRecipeIngredientInputDTO(Set<RecipeIngredient> recipeIngredients) {
        return recipeIngredients.stream()
                .map(this::toRecipeIngredientOutputDTO)
                .collect(Collectors.toSet());
    }
}
