package com.holberton_portfolio_project.BonAppEatIt.repository;

import com.holberton_portfolio_project.BonAppEatIt.models.Ingredient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface IngredientRepository extends JpaRepository<Ingredient, UUID> {}
