package com.holberton_portfolio_project.BonAppEatIt.dto;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class RecipeFiltersDTO {
    private List<String> includeIngredients;    // Must contain these ingredients
    private List<String> excludeIngredients;    // Must NOT contain these

    // Integer rather than int because will default to null instead of 0 if the user doesn't want fo filter
    @Min(value = 2, message = "You can't filter below 2 ingredients")
    private Integer maxIngredients;             // Maximum number of ingredients

    @Min(value = 1, message = "Month must be between 1 and 12")
    @Max(value = 12, message = "Month must be between 1 and 12")
    private Integer month;                      // 1-12 for seasonality
    private Integer maxPrepTime;                // Maximum prep time in minutes
    private List<String> tags;                  // Must have all tags
}
