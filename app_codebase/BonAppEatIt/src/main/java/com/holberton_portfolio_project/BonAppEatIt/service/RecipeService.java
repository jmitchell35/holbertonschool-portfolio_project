package com.holberton_portfolio_project.BonAppEatIt.service;

import com.holberton_portfolio_project.BonAppEatIt.dto.InstructionInputDTO;
import com.holberton_portfolio_project.BonAppEatIt.dto.RecipeCreateDTO;
import com.holberton_portfolio_project.BonAppEatIt.dto.RecipeFiltersDTO;
import com.holberton_portfolio_project.BonAppEatIt.dto.RecipeIngredientInputDTO;
import com.holberton_portfolio_project.BonAppEatIt.dto.RecipeOutputDTO;
import com.holberton_portfolio_project.BonAppEatIt.exceptions.IngredientNotFoundException;
import com.holberton_portfolio_project.BonAppEatIt.exceptions.PublisherNotFoundException;
import com.holberton_portfolio_project.BonAppEatIt.exceptions.TagNotFoundException;
import com.holberton_portfolio_project.BonAppEatIt.exceptions.UnitNotFoundException;
import com.holberton_portfolio_project.BonAppEatIt.exceptions.UserImpersonationException;
import com.holberton_portfolio_project.BonAppEatIt.mappers.RecipeMapper;
import com.holberton_portfolio_project.BonAppEatIt.models.Collection;
import com.holberton_portfolio_project.BonAppEatIt.models.Ingredient;
import com.holberton_portfolio_project.BonAppEatIt.models.Instruction;
import com.holberton_portfolio_project.BonAppEatIt.models.Recipe;
import com.holberton_portfolio_project.BonAppEatIt.models.RecipeIngredient;
import com.holberton_portfolio_project.BonAppEatIt.models.Tag;
import com.holberton_portfolio_project.BonAppEatIt.models.Unit;
import com.holberton_portfolio_project.BonAppEatIt.models.User;
import com.holberton_portfolio_project.BonAppEatIt.repository.CollectionRepository;
import com.holberton_portfolio_project.BonAppEatIt.repository.IngredientRepository;
import com.holberton_portfolio_project.BonAppEatIt.repository.RecipeRepository;
import com.holberton_portfolio_project.BonAppEatIt.repository.TagRepository;
import com.holberton_portfolio_project.BonAppEatIt.repository.UnitRepository;
import com.holberton_portfolio_project.BonAppEatIt.repository.UserRepository;
import com.holberton_portfolio_project.BonAppEatIt.specifications.RecipeSpecifications;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

@AllArgsConstructor
@Service
public class RecipeService {
    private final RecipeRepository recipeRepository;
    private final RecipeMapper recipeMapper;
    private final IngredientRepository ingredientRepository;
    private final UnitRepository unitRepository;
    private final TagRepository tagRepository;
    private final UserRepository userRepository;
    private final CollectionRepository collectionRepository;

    // We need to keep the session with the DB opened so there is time to load tags...
    @Transactional(readOnly = true)
    public Page<RecipeOutputDTO> findFilteredRecipes(RecipeFiltersDTO filters, Pageable pageable) {
        Specification<Recipe> spec = (root, query, criteriaBuilder) ->
                criteriaBuilder.conjunction();

        if (filters.getMaxPrepTime() != null) {
            spec = spec.and(RecipeSpecifications.hasMaxPrepTime(filters.getMaxPrepTime()));
        }

        if (filters.getMaxIngredients() != null) {
            spec = spec.and(RecipeSpecifications.maxIngredients(filters.getMaxIngredients()));
        }

        if (filters.getMonth() != null) {
            spec = spec.and(RecipeSpecifications.hasSeasonalIngredients(filters.getMonth()));
        }

        if (filters.getTagIds() != null && !filters.getTagIds().isEmpty()) {
            spec = spec.and(RecipeSpecifications.hasTags(filters.getTagIds()));
        }

        if (filters.getExcludeIngredients() != null && !filters.getExcludeIngredients().isEmpty()) {
            spec = spec.and(RecipeSpecifications.excludesIngredients(filters.getExcludeIngredients()));
        }

        if (filters.getIncludeIngredients() != null && !filters.getIncludeIngredients().isEmpty()) {
            spec = spec.and(RecipeSpecifications.hasIngredients(filters.getIncludeIngredients()));
        }

        Page<Recipe> recipes = recipeRepository.findAll(spec, pageable);

        return recipeMapper.toOutputDTO(recipes);
    }

    public RecipeOutputDTO createRecipe(RecipeCreateDTO dto, String publishingEmail) {
        validatePublisher(dto.getPublisher(), publishingEmail);

        Recipe recipe = Recipe.builder()
                .name(dto.getName())
                .prepTime(dto.getPrepTime())
                .servings(dto.getServings())
                .publisher(dto.getPublisher())
                .build();

        Set<Instruction> instructions = buildInstructions(dto.getInstructions(), recipe);
        Set<RecipeIngredient> recipeIngredients = buildRecipeIngredients(dto.getRecipeIngredients(), recipe);
        Set<Tag> tags = resolveTagsSet(dto.getTagIds());
        Set<Collection> collections = resolveCollectionsSet(dto.getCollectionIds());

        recipe.setInstructions(instructions);
        recipe.setRecipeIngredients(recipeIngredients);
        recipe.setTags(tags);
        recipe.setRecipeIngredients(recipeIngredients);
        recipe.setCollections(collections);

        Recipe savedRecipe = recipeRepository.save(recipe);

        return recipeMapper.toOutputDTO(savedRecipe);
    }

    private void validatePublisher(String recipePublisher, String publishingEmail) {
        Optional<User> publishingUser = userRepository.findByEmail(publishingEmail);
        if (publishingUser.isEmpty()) {
            throw new PublisherNotFoundException("Publishing user not found");
        }

        if (!publishingUser.get().getUsername().equals(recipePublisher)) {
            throw new UserImpersonationException("Recipe publisher does not belong to this publisher");
        }
    }

    private Set<Instruction> buildInstructions(Set<InstructionInputDTO> instructions, Recipe recipe) {
        return instructions.stream()
                .map(instruction -> Instruction.builder()
                        .recipe(recipe)
                        .text(instruction.getText())
                        .stepNumber(instruction.getStepNumber())
                        .build())
                .collect(Collectors.toSet());
    }

    private Set<RecipeIngredient> buildRecipeIngredients(
            Set<RecipeIngredientInputDTO> recipeIngredients,
            Recipe recipe
    ) {
        return recipeIngredients.stream()
                .map(recipeIngredient -> {
                    Ingredient ingredient = ingredientRepository.findById(recipeIngredient.getIngredientId())
                            .orElseThrow(() -> new IngredientNotFoundException(
                                    recipeIngredient.getIngredientId() + " " +
                                            recipeIngredient.getIngredientId() + " not found"));
                    Unit unit = unitRepository.findById(recipeIngredient.getUnitId())
                            .orElseThrow(() -> new UnitNotFoundException(recipeIngredient.getUnitId() + " " +
                                    recipeIngredient.getUnitId() + " not found"));

                    return RecipeIngredient.builder()
                            .recipe(recipe)
                            .ingredient(ingredient)
                            .unit(unit)
                            .quantity(recipeIngredient.getQuantity())
                            .build();
                })
                .collect(Collectors.toSet());
    }

    private Set<Tag> resolveTagsSet(Set<UUID> tagIds) {
        if (tagIds.isEmpty()) {
            return new HashSet<>();
        }

        List<Tag> foundTags = tagRepository.findAllById(tagIds);

        //noinspection DuplicatedCode
        if (foundTags.size() != tagIds.size()) {
            Set<UUID> foundIds = foundTags.stream()
                    .map(Tag::getId)
                    .collect(Collectors.toSet());

            Set<UUID> missingIds = tagIds.stream()
                    .filter(id -> !foundIds.contains(id))
                    .collect(Collectors.toSet());

            throw new TagNotFoundException("Tags not found: " + missingIds);
        }

        return new HashSet<>(foundTags);
    }

    private Set<Collection> resolveCollectionsSet(Set<UUID> collectionIds) {
        if (collectionIds.isEmpty()) {
            return new HashSet<>();
        }

        List<Collection> foundCollections = collectionRepository.findAllById(collectionIds);

        if (foundCollections.size() != collectionIds.size()) {
            Set<UUID> foundIds = foundCollections.stream()
                    .map(Collection::getId)
                    .collect(Collectors.toSet());

            Set<UUID> missingIds = collectionIds.stream()
                    .filter(id -> !foundIds.contains(id))
                    .collect(Collectors.toSet());

            throw new TagNotFoundException("Tags not found: " + missingIds);
        }

        return new HashSet<>(foundCollections);
    }
}
