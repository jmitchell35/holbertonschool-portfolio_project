package com.holberton_portfolio_project.BonAppEatIt.models;

// jakarta.persistence
import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.Table;
import jakarta.persistence.Column;
import jakarta.persistence.OneToMany;
import jakarta.persistence.CascadeType;
import jakarta.persistence.FetchType;
import jakarta.persistence.AttributeOverride;

// jakarta.
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Email;

// org.springframework.data
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

// Lombok boilerplate methods
import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import lombok.Builder;

// general java utils
import java.time.LocalDateTime;
import java.util.Set;
import java.util.HashSet;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
// inherit id from base class but customize DB column name
@AttributeOverride(name = "id", column = @Column(name = "user_id"))
@Table(name = "users")
@EntityListeners(AuditingEntityListener.class)
public class User extends BaseEntity {

    @Getter @Setter
    @Column(name = "email", nullable = false, unique = true)
    @Email(message = "Email must be valid")
    @NotBlank(message = "Email field is required")
    private String email;

    @Getter // creates getValue() getter method
    // store bcrypt hash with 3-char work factor, future algo safe with default length value 255
    @Column(name = "password", nullable = false) // length = 255 implicit
    @NotBlank
    private String password;

    @Column(name = "password_updated_at", nullable = false)
    private LocalDateTime passwordUpdatedAt;

    @Builder.Default
    @Getter @Setter
    @Column(name = "is_admin",nullable = false)
    private boolean isAdmin = false;

    @Builder.Default
    @Getter @Setter
    @Column(name = "is_verified", nullable = false)
    private boolean isVerified = false;

    @Builder.Default
    @Getter @Setter
    @Column(name = "is_active", nullable = false)
    private boolean isActive = false;

    /*
    This is the referencing side of the relationship
    targetEntity = Entity.class, mappedBy = relatedEntity.field, cascade = CascadeType.CHOOSE_KIND,
    fetch = FetchType.CHOOSE_KIND
     */
    @Builder.Default // Force initializing to avoid pointing to a non-existing collection
    @OneToMany(targetEntity = Collection.class, mappedBy = "owner", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Set<Collection> collections = new HashSet<>();

    public void setPassword(String passwordHash) {
        this.password = passwordHash;
        this.passwordUpdatedAt = LocalDateTime.now();
    }
}
