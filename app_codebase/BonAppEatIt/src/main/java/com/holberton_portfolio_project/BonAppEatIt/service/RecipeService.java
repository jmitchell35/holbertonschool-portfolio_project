package com.holberton_portfolio_project.BonAppEatIt.service;

import com.holberton_portfolio_project.BonAppEatIt.dto.RecipeIngredientLightDTO;
import com.holberton_portfolio_project.BonAppEatIt.dto.RecipeLightDTO;
import com.holberton_portfolio_project.BonAppEatIt.dto.TagDTO;
import com.holberton_portfolio_project.BonAppEatIt.mappers.RecipeIngredientMapper;
import com.holberton_portfolio_project.BonAppEatIt.mappers.TagMapper;
import com.holberton_portfolio_project.BonAppEatIt.models.Recipe;
import com.holberton_portfolio_project.BonAppEatIt.repository.RecipeRepository;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Set;

@AllArgsConstructor
@Service
public class RecipeService {
    private final RecipeRepository recipeRepository;
    private final TagMapper tagMapper;
    private final RecipeIngredientMapper recipeIngredientMapper;

    public Page<RecipeLightDTO> findAllAsLightDTO(Pageable pageable) {
        Page<Recipe> recipePage = recipeRepository.findAll(pageable);

        return recipePage.map(this::convertToLightDTO);
    }

    private RecipeLightDTO convertToLightDTO(Recipe recipe) {
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
}
