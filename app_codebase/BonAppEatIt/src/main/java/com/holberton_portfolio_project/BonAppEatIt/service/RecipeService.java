package com.holberton_portfolio_project.BonAppEatIt.service;

import com.holberton_portfolio_project.BonAppEatIt.dto.RecipeLightDTO;
import com.holberton_portfolio_project.BonAppEatIt.mappers.RecipeMapper;
import com.holberton_portfolio_project.BonAppEatIt.models.Recipe;
import com.holberton_portfolio_project.BonAppEatIt.repository.RecipeRepository;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Service
public class RecipeService {
    private final RecipeRepository recipeRepository;
    private final RecipeMapper recipeMapper;


    public Page<RecipeLightDTO> findAllAsLightDTO(Pageable pageable) {
        Page<Recipe> recipePage = recipeRepository.findAll(pageable);

        return recipeMapper.toLightDTO(recipePage);
    }
}
