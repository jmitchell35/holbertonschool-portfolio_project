package com.holberton_portfolio_project.BonAppEatIt.models;

// jakarta.persistence
import jakarta.persistence.AttributeOverride;
import jakarta.persistence.AttributeOverrides;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.Column;
import jakarta.persistence.EntityListeners;


// org.springframework.data
import jakarta.persistence.FetchType;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

// Lombok boilerplate methods
import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import lombok.Builder;

import java.util.HashSet;
import java.util.Set;


@Builder
@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
@AttributeOverride(name = "id", column = @Column(name = "ingredient_id"))
@Table(name = "ingredients")
@Entity
@EntityListeners(AuditingEntityListener.class)
public class Ingredient extends BaseEntity {
    @Column(name = "ingredient_singular")
    private String ingredientSingular;

    @Column(name = "ingredient_plural")
    private String ingredientPlural;

    @Column(name = "available_in_january")
    private boolean availableInJanuary;

    @Column(name = "available_in_february")
    private boolean availableInFebruary;

    @Column(name = "available_in_march")
    private boolean availableInMarch;

    @Column(name = "available_in_april")
    private boolean availableInApril;

    @Column(name = "available_in_may")
    private boolean availableInMay;

    @Column(name = "available_in_june")
    private  boolean availableInJune;

    @Column(name = "available_in_july")
    private boolean availableInJuly;

    @Column(name = "available_in_august")
    private boolean availableInAugust;

    @Column(name = "available_in_september")
    private boolean availableInSeptember;

    @Column(name = "available_in_october")
    private boolean availableInOctober;

    @Column(name = "available_in_november")
    private boolean availableInNovember;

    @Column(name = "available_in_december")
    private boolean availableInDecember;

    @OneToMany(targetEntity = RecipeIngredient.class, mappedBy = "ingredient", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Set<RecipeIngredient> recipeIngredients = new HashSet<>();
}
