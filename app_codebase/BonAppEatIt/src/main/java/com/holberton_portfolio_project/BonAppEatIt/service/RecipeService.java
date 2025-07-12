package com.holberton_portfolio_project.BonAppEatIt.service;

import com.holberton_portfolio_project.BonAppEatIt.dto.RecipeFiltersDTO;
import com.holberton_portfolio_project.BonAppEatIt.dto.RecipeLightDTO;
import com.holberton_portfolio_project.BonAppEatIt.mappers.RecipeMapper;
import com.holberton_portfolio_project.BonAppEatIt.models.Recipe;
import com.holberton_portfolio_project.BonAppEatIt.repository.RecipeRepository;
import com.holberton_portfolio_project.BonAppEatIt.specifications.RecipeSpecifications;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Service
public class RecipeService {
    private final RecipeRepository recipeRepository;
    private final RecipeMapper recipeMapper;


    public Page<RecipeLightDTO> findFilteredRecipes(RecipeFiltersDTO filters, Pageable pageable) {
        Specification<Recipe> spec = (root, query, criteriaBuilder) ->
                criteriaBuilder.conjunction();

        if (filters.getMaxPrepTime() != null) {
            spec = spec.and(RecipeSpecifications.hasMaxPrepTime(filters.getMaxPrepTime()));
        }

        if (filters.getMaxIngredients() != null) {
            spec = spec.and(RecipeSpecifications.maxIngredients(filters.getMaxIngredients()));
        }

        if (filters.getTags() != null && !filters.getTags().isEmpty()) {
            spec = spec.and(RecipeSpecifications.hasTags(filters.getTags()));
        }

        if (filters.getExcludeIngredients() != null && !filters.getExcludeIngredients().isEmpty()) {
            spec = spec.and(RecipeSpecifications.excludesIngredients(filters.getExcludeIngredients()));
        }

        if (filters.getIncludeIngredients() != null && !filters.getIncludeIngredients().isEmpty()) {
            spec = spec.and(RecipeSpecifications.hasIngredients(filters.getIncludeIngredients()));
        }

        if (filters.getMonth() != null) {
            spec = spec.and(RecipeSpecifications.hasSeasonalIngredients(filters.getMonth()));
        }

        Page<Recipe> recipes = recipeRepository.findAll(spec, pageable);

        return recipeMapper.toLightDTO(recipes);
    }
}
