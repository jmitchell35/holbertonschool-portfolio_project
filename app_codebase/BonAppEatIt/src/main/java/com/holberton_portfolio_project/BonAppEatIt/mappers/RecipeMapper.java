package com.holberton_portfolio_project.BonAppEatIt.mappers;

import com.holberton_portfolio_project.BonAppEatIt.dto.InstructionOutputDTO;
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
    private final InstructionMapper instructionMapper;

    public RecipeOutputDTO toOutputDTO(Recipe recipe) {
        Set<TagDTO> tagsDTO = tagMapper.toTagDTO(recipe.getTags());
        Set<RecipeIngredientOutputDTO> recipeIngredientsDTO = recipeIngredientMapper.toRecipeIngredientOutputDTO(
                recipe.getRecipeIngredients());
        Set<InstructionOutputDTO> instructionsDTO = instructionMapper.toOutputDTO(recipe.getInstructions());

        return RecipeOutputDTO.builder()
                .id(recipe.getId())
                .name(recipe.getName())
                .prepTime(recipe.getPrepTime())
                .servings(recipe.getServings())
                .tags(tagsDTO)
                .thumbnailUrl(recipe.getThumbnailUrl())
                .bannerUrl(recipe.getBannerUrl())
                .recipeIngredients(recipeIngredientsDTO)
                .instructions(instructionsDTO)
                .createdAt(recipe.getCreatedAt())
                .build();
    }

    public Page<RecipeOutputDTO> toOutputDTO(Page<Recipe> recipes) {
        return recipes.map(this::toOutputDTO);
    }
}
