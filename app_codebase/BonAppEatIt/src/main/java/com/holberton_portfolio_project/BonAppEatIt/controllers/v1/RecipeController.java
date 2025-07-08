package com.holberton_portfolio_project.BonAppEatIt.controllers.v1;

import com.holberton_portfolio_project.BonAppEatIt.constants.ApiRoutes;
import com.holberton_portfolio_project.BonAppEatIt.service.RecipeService;
import lombok.AllArgsConstructor;
import org.springframework.validation.annotation.Validated;
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
//    @GetMapping
//    public Page<RecipeLightDTO> getRecipes(
//            @PageableDefault(
//                    page = 0,
//                    size = 20,
//                    sort = "createdAt",
//                    direction = Sort.Direction.DESC
//            ) Pageable pageable
//    ) {
//        Page<Recipe> recipePage = recipeService.findAll(pageable);
//
//        return recipePage.map(this::convertToLightDTO);
//    }
//
//    @PostMapping
//    public RecipeLightDTO createRecipe(@Valid @RequestBody RecipeCreateDTO recipeLightDTO) {}
//
//    @PutMapping
//    public RecipeLightDTO updateRecipe(@Valid @RequestBody RecipeLightDTO recipeLightDTO) {}
//
//    private RecipeLightDTO convertToLightDTO(Recipe recipe) {
//        return RecipeLightDTO.builder()
//                .id(recipe.getId())
//                .name(recipe.getName())
//                .prepTime(recipe.getPrepTime())
//                .servings(recipe.getServings())
//                // Here we transform the set of Tags
//                .tagNames(recipe.getTags().stream()  // stream allows for sequential processing of each tag object
//                        .map(Tag::getName)  // transforms each Tag into only its String name
//                        .collect(Collectors.toSet()))  // collects transformed stream into a new Set object
//                .createdAt(recipe.getCreatedAt())
//                .build();
//    }
}
