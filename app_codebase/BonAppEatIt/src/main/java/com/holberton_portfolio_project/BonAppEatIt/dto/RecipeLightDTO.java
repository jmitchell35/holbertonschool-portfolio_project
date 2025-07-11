package com.holberton_portfolio_project.BonAppEatIt.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@Builder
@Getter
@AllArgsConstructor
public class RecipeLightDTO {
    private UUID id;
    private String name;
    private int prepTime;
    private String servings;

    @Builder.Default
    private Set<TagDTO> tags = new HashSet<>();

    @Builder.Default
    private Set<RecipeIngredientLightDTO> recipeIngredients = new HashSet<>();

    private LocalDateTime createdAt;
}
