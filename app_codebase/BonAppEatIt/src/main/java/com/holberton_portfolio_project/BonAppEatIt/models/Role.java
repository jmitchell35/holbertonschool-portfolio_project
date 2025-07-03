package com.holberton_portfolio_project.BonAppEatIt.models;

import jakarta.persistence.AttributeOverride;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.ManyToMany;
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
@AllArgsConstructor
@NoArgsConstructor
@Setter @Getter
@Entity
@AttributeOverride(name = "id", column = @Column(name = "role_id"))
@Table(name = "roles")
@EntityListeners(AuditingEntityListener.class)
public class Role extends BaseEntity {

    @Column(name = "role", nullable = false, unique = true)
    // "ROLE_ADMIN", "ROLE_USER" following Spring Security convention
    private String role;

    @ManyToMany(mappedBy = "roles")
    private Set<User> users = new HashSet<>();
}
