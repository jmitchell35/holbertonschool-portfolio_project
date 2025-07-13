package com.holberton_portfolio_project.BonAppEatIt.controllers.v1;

import com.holberton_portfolio_project.BonAppEatIt.constants.ApiRoutes;
import com.holberton_portfolio_project.BonAppEatIt.dto.RecipeCreateDTO;
import com.holberton_portfolio_project.BonAppEatIt.dto.RecipeFiltersDTO;
import com.holberton_portfolio_project.BonAppEatIt.dto.RecipeOutputDTO;
import com.holberton_portfolio_project.BonAppEatIt.dto.ResponseSuccessDTO;
import com.holberton_portfolio_project.BonAppEatIt.service.RecipeService;
import com.holberton_portfolio_project.BonAppEatIt.service.ResponseSuccessService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.security.core.Authentication;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@AllArgsConstructor
@RestController
@RequestMapping(ApiRoutes.V1.RECIPES)
@Validated
public class RecipeController {
    private final ResponseSuccessService responseSuccessService;
    private final RecipeService recipeService;

    /*
    Spring automatically injects Pageable which handles request pagination.
    The default query parameters are int page = 0, int size = 20, sort = unsorted
    Accepts requests with URL parameters like :
    GET /api/recipes                     → page=0, size=20, sort=createdAt DESC (below default behavior)
    GET /api/recipes?page=2              → page=2, size=20, sort=createdAt DESC
    GET /api/recipes?size=10             → page=0, size=10, sort=createdAt DESC
    GET /api/recipes?sort=name,asc       → page=0, size=20, sort=name ASC (overrides custom default sort)
     */

    /*
    Spring naturally manages results as Page which have this format :
    {
      "content": [...], // Recipes here
      "pageable": {
        "sort": {"sorted": false, "unsorted": true},
        "pageNumber": 0,
        "pageSize": 20
      },
      "totalElements": 287,
      "totalPages": 15,
      "numberOfElements": 20,
      "size": 20,
      "number": 0,
      "first": true,
      "last": false,
      "empty": false
    }
     */
    @GetMapping
    public ResponseSuccessDTO getAllRecipesPaginated(
            HttpServletRequest request,
            @PageableDefault(
                    page = 0,
                    size = 20,
                    sort = "createdAt",
                    direction = Sort.Direction.DESC
            ) Pageable pageable,
            @ModelAttribute @Valid RecipeFiltersDTO filters
            ) {

        Page<RecipeOutputDTO> recipes = recipeService.findFilteredRecipes(filters, pageable);

        return responseSuccessService.createSuccessResponse(
                request,
                recipes
        );
    }

    @PostMapping
    public ResponseSuccessDTO createRecipe(
            HttpServletRequest request,
            Authentication auth,
            @Valid @RequestBody RecipeCreateDTO recipeCreateDTO
    ) {
        String publishingUser = auth.getName();

        RecipeOutputDTO newRecipe =  recipeService.createRecipe(recipeCreateDTO, publishingUser);

        return responseSuccessService.createSuccessResponse(
                request,
                "Votre recette a bien été enregistrée",
                newRecipe
        );
    }

//    @PutMapping
//    public RecipeLightDTO updateRecipe(@Valid @RequestBody RecipeLightDTO recipeLightDTO) {}


}
