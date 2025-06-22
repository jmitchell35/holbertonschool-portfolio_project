package com.holberton_portfolio_project.BonAppEatIt.models;

// jakarta.persistence
import jakarta.persistence.CascadeType;
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
import jakarta.persistence.UniqueConstraint;

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

@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
@AttributeOverride(name = "id", column  = @Column(name = "collection_id"))
@Entity
@Table(name = "collections")
@EntityListeners(AuditingEntityListener.class)
public class Collection extends BaseEntity {

    @Column(name = "name", nullable = false)
    private String name;

    @ManyToOne(targetEntity = User.class, fetch = FetchType.LAZY)
    private User owner;


    // CascadeType.PERSIST automatically saves new recipes when saving this collection
    @ManyToMany(targetEntity = Recipe.class, cascade = CascadeType.PERSIST, fetch = FetchType.LAZY)
    @JoinTable(
            name = "recipe_collections", // Junction table name
            joinColumns = @JoinColumn(name = "collection_id"),
            inverseJoinColumns = @JoinColumn(name = "recipe_id"),
            uniqueConstraints = @UniqueConstraint(columnNames = {"collection_id", "recipe_id"})
    )
    private Set<Recipe> recipes = new HashSet<>();
}
