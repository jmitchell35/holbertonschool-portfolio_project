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
public class RecipeOutputDTO {
    private UUID id;
    private String name;
    private int prepTime;
    private int servings;

    @Builder.Default
    private Set<TagDTO> tags = new HashSet<>();

    @Builder.Default
    private Set<RecipeIngredientOutputDTO> recipeIngredients = new HashSet<>();

    private LocalDateTime createdAt;
    private String thumbnailUrl;
    private String bannerUrl;

    @Builder.Default
    private Set<InstructionOutputDTO> instructions = new HashSet<>();
}
