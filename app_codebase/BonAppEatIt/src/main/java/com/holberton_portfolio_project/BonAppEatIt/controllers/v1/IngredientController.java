package com.holberton_portfolio_project.BonAppEatIt.controllers.v1;

import com.holberton_portfolio_project.BonAppEatIt.constants.ApiRoutes;
import com.holberton_portfolio_project.BonAppEatIt.dto.ResponseSuccessDTO;
import com.holberton_portfolio_project.BonAppEatIt.models.Ingredient;
import com.holberton_portfolio_project.BonAppEatIt.service.IngredientService;
import com.holberton_portfolio_project.BonAppEatIt.service.ResponseSuccessService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping(ApiRoutes.V1.BASE + "/ingredients")
@ResponseStatus(HttpStatus.OK)
public class IngredientController {

    private final IngredientService ingredientService;
    private final ResponseSuccessService responseSuccessService;

    @GetMapping()
    public ResponseSuccessDTO getAllIngredients(HttpServletRequest request) {
        List<Ingredient> ingredients = ingredientService.getAllIngredients();

        return responseSuccessService.createSuccessResponse(request, ingredients);
    }

    @GetMapping("/search")
    public ResponseSuccessDTO searchIngredients(HttpServletRequest request, @RequestParam String query) {
        List<Ingredient> ingredients = ingredientService.searchIngredients(query);

        return responseSuccessService.createSuccessResponse(request, ingredients);
    }
}
