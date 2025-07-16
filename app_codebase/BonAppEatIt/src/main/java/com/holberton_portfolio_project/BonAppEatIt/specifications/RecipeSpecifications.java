package com.holberton_portfolio_project.BonAppEatIt.specifications;

import com.holberton_portfolio_project.BonAppEatIt.models.Ingredient;
import com.holberton_portfolio_project.BonAppEatIt.models.Recipe;
import com.holberton_portfolio_project.BonAppEatIt.models.RecipeIngredient;
import com.holberton_portfolio_project.BonAppEatIt.models.Tag;
import jakarta.persistence.criteria.Join;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import jakarta.persistence.criteria.Subquery;
import org.springframework.data.jpa.domain.Specification;

import java.util.ArrayList;
import java.util.List;

public class RecipeSpecifications {
    public static Specification<Recipe> hasMaxPrepTime(Integer maxTime) {
        /*
        Root = queried DB entity
        Query = SQL query context. SQL subquery => query.subquery
        CriteriaBuilder = helps building the Predicate / filtering condition
         */
        return (root, query, criteriaBuilder) -> maxTime == null ?
                criteriaBuilder.conjunction()
                : criteriaBuilder.lessThanOrEqualTo(root.get("prepTime"), maxTime);
    }

    /*
    Typical "ORM hell".
    This is why it is sometimes preferred to use QueryDSL, jOOQ or Native SQL to write queries.

    // Native SQL
    @Query(value = """
        SELECT DISTINCT r.* FROM recipes r
        JOIN recipe_tags rt ON r.recipe_id = rt.recipe_id
        JOIN tags t ON rt.tag_id = t.tag_id
        WHERE t.name IN (:tags)
        GROUP BY r.recipe_id
        HAVING COUNT(DISTINCT t.name) = :tagCount
        """, nativeQuery = true)
    Page<Recipe> findRecipesWithAllTags(@Param("tags") List<String> tags,
                                       @Param("tagCount") int tagCount,
                                       Pageable pageable);

    // Libraries like QueryDSL, JOOQ
    QRecipe recipe = QRecipe.recipe;
    QTag tag = QTag.tag;

    List<Recipe> recipes = queryFactory
        .selectFrom(recipe)
        .join(recipe.tags, tag)
        .where(tag.name.in(tagNames))
        .groupBy(recipe.id)
        .having(tag.count().eq(tagNames.size()))
        .fetch();

     */
    public static Specification<Recipe> hasTags(List<String> tagNames) {
        return (root, query, criteriaBuilder) -> {
            if  (tagNames.isEmpty()) {
                return criteriaBuilder.conjunction();
            }
          /*
          Join<Recipe, Tag>  recipeTagJoin = root.join("tags");
          This doesn't work here because we need to check if each tagNames exist in each recipe's tags list.
          Which means that we somehow need a query context for each of them (for as many SQL EXISTS checks).
          And since we need one query for each tagName, we need a predicate for each.
           */

            List<Predicate> predicates = new ArrayList<>();

            for (String tagName : tagNames) {
                // Subquery nested into the main SQL query
                Subquery<Long> subquery = query.subquery(Long.class);
                // SQL scoping / query context: each query context needs its own table reference
                Root<Recipe> subRoot = subquery.from(Recipe.class);
                // One tagName = one EXIST question to a table or joint => One join for each tagName
                Join<Recipe, Tag> subTagJoin = subRoot.join("tags");

                // SELECT r2.id FROM recipes r2
                subquery.select(subRoot.get("id")) // take a recipe row in the sub-queried table
                        .where(criteriaBuilder.and(
                                // Where the query join and subquery join IDs match
                                criteriaBuilder.equal(subRoot.get("id"), root.get("id")),
                                // And where the subquery join has tagName in the "name" field
                                criteriaBuilder.equal(subTagJoin.get("name"), tagName))
                        );

                // Check if that subquery returns any row, add it to our list of required conditions (predicates)
                predicates.add(criteriaBuilder.exists(subquery));
            }
          /*
          We need to return a predicate which combines all conditions

          criteriaBuilder.and() accepts :
          // Option 1: Two specific predicates
          Predicate and(Expression<Boolean> x, Expression<Boolean> y)

          // Option 2: Variable number of predicates (varargs added in Java 5) => variadic function
          Predicate and(Predicate... predicates)

          In Java, the compiler always converts variadic arguments into an array (kind of like C's argv[]).
          So although the signature expects a variable number of args, the compiler implicitly accepts an array.
          We have a ArrayList of Predicates.
          So the array is the closest point of understanding between our ArrayList of Predicates, and the variadic
          signature of criteriaBuilder.and().
          One quickly converts to the other, and the compiler would get there anyway.
          But the compiler expects the conversion to an array to be explicit to process the predicates.
          This explicit conversion maintains type-safety and backward-compatibility with pre-Java 5 / varargs code.

          Result: Returns recipes that have ALL specified tags (AND logic)
           */
            return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
        };
    }

    public static Specification<Recipe> hasIngredients(List<String> ingredients) {
        // Below parameters could be : queriedEntity, query, nullableWhereClause
        return (root,
                query,
                criteriaBuilder) -> {
            if (ingredients.isEmpty()) {
                return criteriaBuilder.conjunction();
            }

            List<Predicate> predicates = new ArrayList<>();

            for (String ingredient : ingredients) {
                Subquery<Long> subquery = query.subquery(Long.class);
                Root<Recipe> subRoot = subquery.from(Recipe.class);
                Join<Recipe, RecipeIngredient> subRecipeIngredientJoin = subRoot.join("recipeIngredients");
                Join<RecipeIngredient, Ingredient> subIngredientJoin = subRecipeIngredientJoin.join("ingredient");

                subquery.select(subRoot.get("id"))
                        .where(criteriaBuilder.and(
                                criteriaBuilder.equal(subRoot.get("id"), root.get("id")),
                                criteriaBuilder.equal(subIngredientJoin.get("ingredientSingular"), ingredient)
                        ));
                predicates.add(criteriaBuilder.exists(subquery));
            }
            return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
        };
    }

    public static Specification<Recipe> excludesIngredients(List<String> ingredients) {
        return (root, query, criteriaBuilder) -> {
            if (ingredients.isEmpty()) {
                return criteriaBuilder.conjunction();
            }

            Subquery<Long> subquery = query.subquery(Long.class);
            Root<Recipe> subRoot = subquery.from(Recipe.class);
            Join<Recipe, RecipeIngredient> subRecipeIngredientJoin = subRoot.join("recipeIngredients");
            Join<RecipeIngredient, Ingredient> subIngredientJoin = subRecipeIngredientJoin.join("ingredient");

            subquery.select(subRoot.get("id"))
                    .where(criteriaBuilder.and(
                            criteriaBuilder.equal(subRoot.get("id"), root.get("id")),
                            subIngredientJoin.get("ingredientSingular").in(ingredients)
                    ));

            return criteriaBuilder.not(criteriaBuilder.exists(subquery));
        };
    }

    public static Specification<Recipe> maxIngredients(Integer maxIngredients) {
        return ((root, query, criteriaBuilder) -> maxIngredients == null ?
                criteriaBuilder.conjunction() :
                criteriaBuilder.lessThanOrEqualTo(criteriaBuilder.size(root.get("recipeIngredients")), maxIngredients)
                );
    }

    public static Specification<Recipe> hasSeasonalIngredients(Integer month) {
        return ((root, query, criteriaBuilder) -> {
            // one-line if statement
            if (month == null) return  criteriaBuilder.conjunction();

            Subquery<Long> subquery = query.subquery(Long.class);
            Root<Recipe> subRoot = subquery.from(Recipe.class);
            Join<Recipe, RecipeIngredient> subRecipeIngredientJoin = subRoot.join("recipeIngredients");
            Join<RecipeIngredient, Ingredient> subIngredientJoin = subRecipeIngredientJoin.join("ingredient");

            String monthField = getMonthFieldName(month);

            subquery.select(subRoot.get("id"))
                    .where(criteriaBuilder.and(
                            criteriaBuilder.equal(subRoot.get("id"), root.get("id")),
                            criteriaBuilder.isFalse(subIngredientJoin.get(monthField))));

            return criteriaBuilder.not(criteriaBuilder.exists(subquery));
        });
    }

    private static String getMonthFieldName(Integer month) {
        return switch (month) {
            case 1 -> "availableInJanuary";
            case 2 -> "availableInFebruary";
            case 3 -> "availableInMarch";
            case 4 -> "availableInApril";
            case 5 -> "availableInMay";
            case 6 -> "availableInJune";
            case 7 -> "availableInJuly";
            case 8 -> "availableInAugust";
            case 9 -> "availableInSeptember";
            case 10 -> "availableInOctober";
            case 11 -> "availableInNovember";
            case 12 -> "availableInDecember";
            default -> throw new IllegalArgumentException("Invalid month: " + month);
        };
    }
}
