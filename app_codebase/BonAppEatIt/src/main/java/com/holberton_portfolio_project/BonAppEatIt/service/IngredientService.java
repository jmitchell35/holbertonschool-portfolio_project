package com.holberton_portfolio_project.BonAppEatIt.service;

import com.holberton_portfolio_project.BonAppEatIt.dto.IngredientDTO;
import com.holberton_portfolio_project.BonAppEatIt.mappers.IngredientMapper;
import com.holberton_portfolio_project.BonAppEatIt.models.Ingredient;
import com.holberton_portfolio_project.BonAppEatIt.repository.IngredientRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@AllArgsConstructor
@Service
public class IngredientService {
    private final IngredientRepository ingredientRepository;
    private final IngredientMapper ingredientMapper;

    public List<IngredientDTO> getAllIngredients() {

        List<Ingredient> ingredients = ingredientRepository.findAll();

        return ingredientMapper.toOutputDTO(ingredients);
    }

    public List<IngredientDTO> searchIngredients(String query) {

        List<Ingredient> ingredients = ingredientRepository.findByIngredientSingularStartsWithIgnoreCase(query);

        return ingredientMapper.toOutputDTO(ingredients);
    }
}
