package com.holberton_portfolio_project.BonAppEatIt.repository;

import com.holberton_portfolio_project.BonAppEatIt.models.Recipe;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;
import java.util.List;

@Repository
public interface RecipeRepository extends JpaRepository<Recipe, UUID> {
    List<Recipe> findByTags_Name(String tagName);
    List<Recipe> findByTags_NameIn(List<String> tagNames);
    List<Recipe> findByTags_NameNotIn(List<String> tagNames);
    List<Recipe> findByIngredients_Name(String ingredientName);
    List<Recipe> findByIngredients_NameNotIn(List<String> ingredientNames);
    List<Recipe> findByIngredients_NameIn(List<String> ingredientNames);

    /*
    Complex queries may need to write custom SQL queries using Java Persistence Query Language
    Expected syntax is :
    SELECT [alias] FROM [EntityName] [alias] WHERE [alias.property] = [condition]
    The common convention is to use "u" as User's alias, "r" as Recipe's alias etc.

    !!!
    JPQL uses ENTITY (User) names and PROPERTY (User.Id) names (camelCase)
    Native SQL uses TABLE (users) names and COLUMN (user_id) names
    @Query(value = "SELECT * FROM recipes WHERE prep_time < :time", nativeQuery = true)
    !!!

    JOINS

    IMPLICIT JOINS

    // JPQL automatically handles joins when you navigate relationships
    @Query("SELECT r FROM Recipe r WHERE r.publisher.email = :email")
    List<Recipe> findRecipesByPublisherEmail(@Param("email") String email);
    // Automatically joins Recipe → User via publisher relationship

    @Query("SELECT r FROM Recipe r WHERE r.tags.name = :tagName")
    List<Recipe> findRecipesByTagName(@Param("tagName") String tagName);
    // Automatically joins Recipe → recipe_tags → Tag

    EXPLICIT JOINS

    INNER JOIN

    // JOIN with your many-to-many relationship
    @Query("SELECT r FROM Recipe r JOIN r.tags t WHERE t.name = :tagName")
    List<Recipe> findRecipesByTag(@Param("tagName") String tagName);

    // Multiple JOINs
    @Query("SELECT r FROM Recipe r JOIN r.tags t JOIN r.collections c WHERE t.name = :tag AND c.owner.email = :email")
    List<Recipe> findRecipesByTagAndOwner(@Param("tag") String tag, @Param("email") String email);

    // JOIN with conditions
    @Query("SELECT r FROM Recipe r JOIN r.recipeIngredients ri JOIN ri.ingredient i WHERE i.ingredientSingular = :ingredient")
    List<Recipe> findRecipesByIngredient(@Param("ingredient") String ingredient);

    LEFT JOIN (get users with their collections even if they have no collection)

    // Get users and their collections (even if they have no collections)
    @Query("SELECT u FROM User u LEFT JOIN u.collections c")
    List<User> findAllUsersWithCollections();

    // Count recipes per user (including users with 0 recipes)
    @Query("SELECT u.email, COUNT(r) FROM User u LEFT JOIN Collection c ON c.owner = u LEFT JOIN c.recipes r GROUP BY u.email")
    List<Object[]> countRecipesPerUser();

    FETCH JOINs (Solve N+1 problem)

    // Load recipes WITH their tags in one query (avoids lazy loading issues)
    @Query("SELECT DISTINCT r FROM Recipe r LEFT JOIN FETCH r.tags WHERE r.prepTime < :maxTime")
    List<Recipe> findQuickRecipesWithTags(@Param("maxTime") int maxTime);

    // Load recipes with ingredients and units
    @Query("SELECT DISTINCT r FROM Recipe r LEFT JOIN FETCH r.recipeIngredients ri LEFT JOIN FETCH ri.ingredient LEFT JOIN FETCH ri.unit")
    List<Recipe> findAllRecipesWithIngredients();

    COMPLEX JOINS WITH AGGREGATION

    // Find recipes with more than X tags
    @Query("SELECT r FROM Recipe r JOIN r.tags t GROUP BY r HAVING COUNT(t) > :minTags")
    List<Recipe> findRecipesWithManyTags(@Param("minTags") long minTags);

    // Find most popular ingredients
    @Query("SELECT i.ingredientSingular, COUNT(r) FROM Recipe r JOIN r.recipeIngredients ri JOIN ri.ingredient i GROUP BY i.ingredientSingular ORDER BY COUNT(r) DESC")
    List<Object[]> findMostPopularIngredients();

    // INNER JOIN
    @Query(value = """
        SELECT r.* FROM recipes r
        INNER JOIN recipe_tags rt ON r.recipe_id = rt.recipe_id
        INNER JOIN tags t ON rt.tag_id = t.tag_id
        WHERE t.name = :tagName
        """, nativeQuery = true)
    List<Recipe> findRecipesByTagNative(@Param("tagName") String tagName);

    // LEFT JOIN with aggregation
    @Query(value = """
        SELECT r.*, COUNT(ri.ingredient_id) as ingredient_count
        FROM recipes r
        LEFT JOIN recipe_ingredients ri ON r.recipe_id = ri.recipe_id
        GROUP BY r.recipe_id
        HAVING COUNT(ri.ingredient_id) <= :maxIngredients
        """, nativeQuery = true)
    List<Object[]> findSimpleRecipesNative(@Param("maxIngredients") int maxIngredients);

    // Multiple JOINs with window functions (PostgreSQL specific)
    @Query(value = """
        SELECT r.name, r.prep_time,
               STRING_AGG(t.name, ', ') as tags,
               COUNT(ri.ingredient_id) as ingredient_count,
               RANK() OVER (ORDER BY r.prep_time) as time_rank
        FROM recipes r
        LEFT JOIN recipe_tags rt ON r.recipe_id = rt.recipe_id
        LEFT JOIN tags t ON rt.tag_id = t.tag_id
        LEFT JOIN recipe_ingredients ri ON r.recipe_id = ri.recipe_id
        WHERE r.prep_time <= :maxTime
        GROUP BY r.recipe_id, r.name, r.prep_time
        ORDER BY r.prep_time
        """, nativeQuery = true)
    List<Object[]> findDetailedQuickRecipes(@Param("maxTime") int maxTime);

    QUERY PARAMETERS MAPPING

    // ❌ Hard to read - which parameter is which?
    @Query("SELECT r FROM Recipe r WHERE r.prepTime < ?1 AND r.servings = ?2 AND r.name LIKE ?3")
    List<Recipe> findRecipes(int maxTime, String servings, String namePattern);

    // ✅ Clear and readable
    @Query("SELECT r FROM Recipe r WHERE r.prepTime < :maxTime AND r.servings = :servings AND r.name LIKE :pattern")
    List<Recipe> findRecipes(@Param("maxTime") int maxTime, @Param("servings") String servings, @Param("pattern") String pattern);

     */
    @Query("SELECT r FROM Recipe r WHERE SIZE(r.recipeIngredients) = :ingredientsCount")
    List<Recipe> findByRecipeIngredientsSize(int ingredientsCount);

    @Query("SELECT r FROM Recipe r WHERE SIZE(r.recipeIngredients) <= :maxIngredients")
    List<Recipe> findByRecipeIngredientsSizeLessThan(int maxIngredients);

    @Query("""
    SELECT r FROM Recipe r
    LEFT JOIN FETCH r.recipeIngredients ri
        LEFT JOIN FETCH ri.ingredient
        LEFT JOIN FETCH ri.unit
    LEFT JOIN FETCH r.tags
    LEFT JOIN r.instructions
    WHERE r.id = :recipeId
    """)
    Optional<Recipe> findByIdWithAllDetails(@Param("recipeId") UUID recipeId);

}
