package com.holberton_portfolio_project.BonAppEatIt.controllers.v1;

import com.holberton_portfolio_project.BonAppEatIt.dto.RecipeCreateDTO;
import com.holberton_portfolio_project.BonAppEatIt.dto.RecipeLightDTO;
import com.holberton_portfolio_project.BonAppEatIt.models.Recipe;
import com.holberton_portfolio_project.BonAppEatIt.models.Tag;
import com.holberton_portfolio_project.BonAppEatIt.service.RecipeService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.stream.Collectors;

@AllArgsConstructor
@RestController
@RequestMapping("/recipes")
@Validated
public class RecipeController extends BaseV1Controller {

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
    public Page<RecipeLightDTO> getRecipes(
            @PageableDefault(
                    page = 0,
                    size = 20,
                    sort = "createdAt",
                    direction = Sort.Direction.DESC
            ) Pageable pageable
    ) {
        Page<Recipe> recipePage = recipeService.findAll(pageable);

        return recipePage.map(this::convertToLightDTO);
    }

    @PostMapping
    public RecipeLightDTO createRecipe(@Valid @RequestBody RecipeCreateDTO recipeLightDTO) {}

    @PutMapping
    public RecipeLightDTO updateRecipe(@Valid @RequestBody RecipeLightDTO recipeLightDTO) {}

    private RecipeLightDTO convertToLightDTO(Recipe recipe) {
        return RecipeLightDTO.builder()
                .id(recipe.getId())
                .name(recipe.getName())
                .prepTime(recipe.getPrepTime())
                .servings(recipe.getServings())
                // Here we transform the set of Tags
                .tagNames(recipe.getTags().stream()  // stream allows for sequential processing of each tag object
                        .map(Tag::getName)  // transforms each Tag into only its String name
                        .collect(Collectors.toSet()))  // collects transformed stream into a new Set object
                .createdAt(recipe.getCreatedAt())
                .build();
    }
}
