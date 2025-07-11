package com.holberton_portfolio_project.BonAppEatIt.mappers;

import com.holberton_portfolio_project.BonAppEatIt.dto.RecipeIngredientLightDTO;
import com.holberton_portfolio_project.BonAppEatIt.dto.RecipeLightDTO;
import com.holberton_portfolio_project.BonAppEatIt.dto.TagDTO;
import com.holberton_portfolio_project.BonAppEatIt.models.Recipe;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;

import java.util.Set;

@AllArgsConstructor
@Component
public class RecipeMapper {
    private final TagMapper tagMapper;
    private final RecipeIngredientMapper recipeIngredientMapper;

    public RecipeLightDTO toLightDTO(Recipe recipe) {
        Set<TagDTO> tagsDTO = tagMapper.toTagDTO(recipe.getTags());
        Set<RecipeIngredientLightDTO> recipeIngredientsDTO = recipeIngredientMapper.toRecipeIngredientDTO(
                recipe.getRecipeIngredients());

        return RecipeLightDTO.builder()
                .id(recipe.getId())
                .name(recipe.getName())
                .prepTime(recipe.getPrepTime())
                .servings(recipe.getServings())
                .tags(tagsDTO)
                .recipeIngredients(recipeIngredientsDTO)
                .createdAt(recipe.getCreatedAt())
                .build();
    }

    public Page<RecipeLightDTO> toLightDTO(Page<Recipe> recipes) {
        return recipes.map(this::toLightDTO);
    }
}
