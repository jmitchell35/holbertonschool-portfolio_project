package com.holberton_portfolio_project.BonAppEatIt.models;

// jakarta.persistence
import jakarta.persistence.AttributeOverride;
import jakarta.persistence.Entity;
import jakarta.persistence.Column;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.UniqueConstraint;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import jakarta.persistence.OneToMany;
import jakarta.persistence.CascadeType;


// org.springframework.data
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

// Lombok boilerplate methods
import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import lombok.Builder;

import java.util.HashSet;
import java.util.Set;


@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
@AttributeOverride(name = "id", column  = @Column(name = "recipe_id"))
@Table(name = "recipes")
@Entity
@EntityListeners(AuditingEntityListener.class)
public class Recipe extends BaseEntity {

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "prep_time", nullable = false)
    private int prepTime;

    @Column(name = "servings", nullable = false)
    private String servings;

    @Column(name = "publisher_id")
    private String publisher;

    @ManyToMany(mappedBy = "recipes", fetch = FetchType.LAZY)
    private Set<Collection> collections = new HashSet<>();

    @ManyToMany(targetEntity = Tag.class, fetch = FetchType.EAGER)
    @JoinTable(
            name = "recipe_tags",
            joinColumns = @JoinColumn(name = "recipe_id"),
            inverseJoinColumns = @JoinColumn(name = "tag_id"),
            uniqueConstraints = @UniqueConstraint(columnNames = {"recipe_id", "tag_id"})
            )
    private Set<Tag> tags = new HashSet<>();

    @OneToMany(targetEntity = Instruction.class, mappedBy = "recipe", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private Set<Instruction> instructions = new HashSet<>();
}
