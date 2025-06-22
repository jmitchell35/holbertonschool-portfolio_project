package com.holberton_portfolio_project.BonAppEatIt.models;


import lombok.Getter;
import lombok.Setter;
import lombok.Builder;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.AttributeOverride;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.Column;
import jakarta.persistence.FetchType;

import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@Getter @Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@AttributeOverride(name = "id", column = @Column(name = "recipe_ingredient_id"))
@Table(name = "recipe_ingredients")
@Entity
@EntityListeners(AuditingEntityListener.class)
public class RecipeIngredient extends BaseEntity {

    @ManyToOne(targetEntity = Recipe.class, fetch = FetchType.LAZY)
    private Recipe recipe;

    @ManyToOne(targetEntity =  Ingredient.class, fetch = FetchType.LAZY)
    private Ingredient ingredient;

    @ManyToOne(targetEntity = Unit.class, fetch = FetchType.LAZY)
    private Unit unit;

    @Column(name = "quantity")
    private short quantity;
}
