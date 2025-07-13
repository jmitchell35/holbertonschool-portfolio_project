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

    @Min(value = 2, message = "You can't filter below 2 ingredients")
    private int maxIngredients;             // Maximum number of ingredients

    @Min(value = 1, message = "Month must be between 1 and 12")
    @Max(value = 12, message = "Month must be between 1 and 12")
    private int month;                      // 1-12 for seasonality (current month)
    private int maxPrepTime;                // Maximum prep time in minutes
    private List<String> tags;                  // Must have these tags
}
