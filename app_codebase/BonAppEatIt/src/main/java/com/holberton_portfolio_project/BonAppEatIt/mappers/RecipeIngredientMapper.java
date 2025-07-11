package com.holberton_portfolio_project.BonAppEatIt.mappers;

import com.holberton_portfolio_project.BonAppEatIt.dto.RecipeIngredientLightDTO;
import com.holberton_portfolio_project.BonAppEatIt.models.RecipeIngredient;
import org.springframework.stereotype.Component;

import java.util.Set;
import java.util.stream.Collectors;

@Component
public class RecipeIngredientMapper {
    public RecipeIngredientLightDTO toRecipeIngredientDTO(RecipeIngredient recipeIngredient) {
        return RecipeIngredientLightDTO.builder()
                .ingredient(recipeIngredient.getIngredient())
                .quantity(recipeIngredient.getQuantity())
                .unit(recipeIngredient.getUnit())
                .build();
    }

    public Set<RecipeIngredientLightDTO> toRecipeIngredientDTO(Set<RecipeIngredient> recipeIngredients) {
        return recipeIngredients.stream()
                .map(this::toRecipeIngredientDTO)
                .collect(Collectors.toSet());
    }
}
