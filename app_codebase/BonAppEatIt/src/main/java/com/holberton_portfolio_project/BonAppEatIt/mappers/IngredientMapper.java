package com.holberton_portfolio_project.BonAppEatIt.mappers;

import com.holberton_portfolio_project.BonAppEatIt.dto.IngredientDTO;
import com.holberton_portfolio_project.BonAppEatIt.models.Ingredient;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class IngredientMapper {
    public IngredientDTO toOutputDTO(Ingredient ingredient) {
        return IngredientDTO.builder()
                .id(ingredient.getId())
                .ingredientSingular(ingredient.getIngredientSingular())
                .ingredientPlural(ingredient.getIngredientPlural())
                .build();
    }

    public List<IngredientDTO> toOutputDTO(List<Ingredient> ingredients) {
        return ingredients.stream()
                .map(this::toOutputDTO)
                .collect(Collectors.toList());
    }
}
