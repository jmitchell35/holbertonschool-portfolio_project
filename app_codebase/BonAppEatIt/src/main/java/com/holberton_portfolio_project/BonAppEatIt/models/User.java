package com.holberton_portfolio_project.BonAppEatIt.models;

// jakarta.persistence
import jakarta.persistence.Entity;
import jakarta.persistence.Column;
import jakarta.persistence.EntityListeners;

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

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@EntityListeners(AuditingEntityListener.class)
public class User extends BaseEntity {

    @Getter @Setter
    @Column(name = "email", nullable = false, unique = true)
    @Email(message = "Email must be valid")
    @NotBlank(message = "Email field is required")
    private String email;

    @Getter
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

    public void setPassword(String passwordHash) {
        this.password = passwordHash;
        this.passwordUpdatedAt = LocalDateTime.now();
    }
}
