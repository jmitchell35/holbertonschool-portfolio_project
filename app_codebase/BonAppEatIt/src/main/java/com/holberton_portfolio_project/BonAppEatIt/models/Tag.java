package com.holberton_portfolio_project.BonAppEatIt.models;

// jakarta.persistence
import jakarta.persistence.AttributeOverride;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.Column;
import jakarta.persistence.ManyToMany;

// Java utils
import java.util.Set;
import java.util.HashSet;

// org.springframework.data
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

// Lombok boilerplate methods
import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import lombok.Builder;


@Getter @Setter
@Builder // smart parameter collector (passed arguments, @Builder.Defaults + parent classes items)
@NoArgsConstructor
@AllArgsConstructor // called by @Builder with retrieved parameters
@Entity
@EntityListeners(AuditingEntityListener.class)
@AttributeOverride(name = "id", column = @Column(name = "tag_id"))
@Table(name = "tags")
public class Tag extends BaseEntity {

    @Builder.Default // Initialized as such if not passed as parameter
    @ManyToMany(mappedBy = "tags")
    private Set<Recipe> recipes = new HashSet<>(); // can't be null

    @Column(name = "name", nullable = false, unique = true)
    private String name;

    @Column(name = "color_hex", length = 7, nullable = false)  // For UI theming
    private String colorHex;
}
