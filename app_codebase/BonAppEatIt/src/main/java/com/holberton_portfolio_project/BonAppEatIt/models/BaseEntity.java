package com.holberton_portfolio_project.BonAppEatIt.models;

// jakarta.persistence imports : JPA tools (data interfaces + Hibernate data methods)
import jakarta.persistence.*;

// lombok imports : auto-build setters and getters
import lombok.Getter;
import lombok.Setter;

// org.springframework.data : additional spring layer on top of JPA
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

// general java tools
import java.time.LocalDateTime;
import java.util.UUID;

@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
@Getter @Setter
public abstract class BaseEntity {

    @Id
    @GeneratedValue
    @Column(name = "id", updatable = false, nullable = false)
    private UUID id;  // Let JPA generate this

    @CreatedDate
    @Column(name = "created_at", nullable = false, updatable = false)
    private LocalDateTime createdAt;

    @LastModifiedDate
    @Column(name = "updated_at")
    private LocalDateTime updatedAt;
}
