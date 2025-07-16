package com.holberton_portfolio_project.BonAppEatIt.mappers;

import com.holberton_portfolio_project.BonAppEatIt.dto.RecipeIngredientOutputDTO;
import com.holberton_portfolio_project.BonAppEatIt.dto.RecipeOutputDTO;
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

    public RecipeOutputDTO toOutputDTO(Recipe recipe) {
        Set<TagDTO> tagsDTO = tagMapper.toTagDTO(recipe.getTags());
        Set<RecipeIngredientOutputDTO> recipeIngredientsDTO = recipeIngredientMapper.toRecipeIngredientOutputDTO(
                recipe.getRecipeIngredients());

        return RecipeOutputDTO.builder()
                .id(recipe.getId())
                .name(recipe.getName())
                .prepTime(recipe.getPrepTime())
                .servings(recipe.getServings())
                .tags(tagsDTO)
                .thumbnailUrl(recipe.getThumbnailUrl())
                .bannerUrl(recipe.getBannerUrl())
                .recipeIngredients(recipeIngredientsDTO)
                .createdAt(recipe.getCreatedAt())
                .build();
    }

    public Page<RecipeOutputDTO> toOutputDTO(Page<Recipe> recipes) {
        return recipes.map(this::toOutputDTO);
    }
}
