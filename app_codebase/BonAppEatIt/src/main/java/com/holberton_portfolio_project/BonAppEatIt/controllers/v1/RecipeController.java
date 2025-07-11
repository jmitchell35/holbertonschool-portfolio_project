package com.holberton_portfolio_project.BonAppEatIt.controllers.v1;

import com.holberton_portfolio_project.BonAppEatIt.constants.ApiRoutes;
import com.holberton_portfolio_project.BonAppEatIt.dto.RecipeLightDTO;
import com.holberton_portfolio_project.BonAppEatIt.service.RecipeService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@AllArgsConstructor
@RestController
@RequestMapping(ApiRoutes.V1.RECIPES)
@Validated
public class RecipeController {

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
    @GetMapping
    public Page<RecipeLightDTO> getAllRecipesPaginated(
            @PageableDefault(
                    page = 0,
                    size = 20,
                    sort = "createdAt",
                    direction = Sort.Direction.DESC
            ) Pageable pageable
    ) {
        return recipeService.findAllAsLightDTO(pageable);
    }

//    @PostMapping
//    public RecipeLightDTO createRecipe(@Valid @RequestBody RecipeCreateDTO recipeLightDTO) {}
//
//    @PutMapping
//    public RecipeLightDTO updateRecipe(@Valid @RequestBody RecipeLightDTO recipeLightDTO) {}


}
