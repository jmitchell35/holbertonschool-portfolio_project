package com.holberton_portfolio_project.BonAppEatIt.models;

import jakarta.persistence.AttributeOverride;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.FetchType;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.util.HashSet;
import java.util.Set;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@AttributeOverride(name = "id", column = @Column(name = "unit_id"))
@Table(name = "units")
@Entity
@EntityListeners(AuditingEntityListener.class)
public class Unit extends BaseEntity {

    @Column(name = "name")
    private String name;

    @Builder.Default
    @OneToMany(targetEntity = RecipeIngredient.class, mappedBy = "unit", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Set<RecipeIngredient> recipeIngredients = new HashSet<>();

}
