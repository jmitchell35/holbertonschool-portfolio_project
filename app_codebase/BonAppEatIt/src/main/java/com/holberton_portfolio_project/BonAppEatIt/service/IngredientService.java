package com.holberton_portfolio_project.BonAppEatIt.service;

import com.holberton_portfolio_project.BonAppEatIt.models.Ingredient;
import com.holberton_portfolio_project.BonAppEatIt.repository.IngredientRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@AllArgsConstructor
@Service
public class IngredientService {
    private final IngredientRepository ingredientRepository;

    public List<Ingredient> getAllIngredients() {
        return ingredientRepository.findAll();
    }

    public List<Ingredient> searchIngredients(String query) {
        return ingredientRepository.findByIngredientSingularStartsWithIgnoreCase(query);
    }
}
