package com.holberton_portfolio_project.BonAppEatIt.models;
import com.holberton_portfolio_project.BonAppEatIt.models.Recipe;

// jakarta.persistence
import jakarta.persistence.Entity;
import jakarta.persistence.Column;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.FetchType;
import jakarta.persistence.AttributeOverride;

// org.springframework.data
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

// Lombok boilerplate methods
import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

// java.util
import java.util.Set;
import java.util.HashSet;


@NoArgsConstructor
@AllArgsConstructor
@Entity
@AttributeOverride(name = "id", column  = @Column(name = "collection_id"))
@Table(name = "collections")
@EntityListeners(AuditingEntityListener.class)
public class Collection extends BaseEntity {

    @Getter @Setter
    @Column(name = "name", nullable = false)
    private String name;

    @Getter @Setter
    @ManyToOne(fetch = FetchType.LAZY)
    private User owner;

    @Getter @Setter
    @ManyToMany(targetEntity = Recipe.class, fetch = FetchType.LAZY)
    @JoinTable(
            name = "recipe_collections", // Junction table name
            joinColumns = @JoinColumn(name = "collection_id"),
            inverseJoinColumns = @JoinColumn(name = "recipe_id")
    )
    private Set<Recipe> recipes = new HashSet<>();
}
