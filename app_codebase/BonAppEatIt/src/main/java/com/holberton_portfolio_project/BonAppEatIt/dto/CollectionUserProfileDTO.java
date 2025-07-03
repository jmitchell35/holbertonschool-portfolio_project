package com.holberton_portfolio_project.BonAppEatIt.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@Getter @Setter
@AllArgsConstructor
public class CollectionUserProfileDTO {
    private String name;
    private int numberOfRecipes;
}
