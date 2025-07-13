package com.holberton_portfolio_project.BonAppEatIt.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@Builder
@Getter
@AllArgsConstructor
public class RecipeCreateDTO {
    @NotBlank(message = "You must give the recipe a name")
    private String name;

    @NotNull(message = "Please indicate the preparation time")
    @Min(value = 1, message = "Preparation time cannot be 0 minutes")
    private int prepTime;

    @NotNull(message = "Please indicate the number of servings")
    @Min(value = 1, message = "The number of servings cannot be 0")
    private int servings;

    @NotBlank(message = "Please reference the publisher")
    private String publisher;

    private Set<UUID> collectionIds = new HashSet<>();
    private Set<UUID> tagIds = new HashSet<>();

    @NotEmpty(message = "Please provide the instructions to cook your recipe")
    private Set<InstructionInputDTO> instructions;

    @NotEmpty(message = "Please provide an ingredients list")
    private Set<RecipeIngredientInputDTO> recipeIngredients;
}
