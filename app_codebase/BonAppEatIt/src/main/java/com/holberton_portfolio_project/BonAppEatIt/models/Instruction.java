package com.holberton_portfolio_project.BonAppEatIt.models;

// jakarta.persistence
import jakarta.persistence.Entity;
import jakarta.persistence.Column;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.AttributeOverride;
import jakarta.persistence.FetchType;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;


// org.springframework.data
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

// Lombok boilerplate methods
import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import lombok.Builder;

@Getter @Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@AttributeOverride(name = "id", column = @Column(name = "instruction_id"))
@Table(name = "instructions")
@Entity
@EntityListeners(AuditingEntityListener.class)
public class Instruction extends BaseEntity {

    @Column(name = "text")
    private String text;

    @Column(name = "step_number")
    private int stepNumber;

    @ManyToOne(targetEntity = Recipe.class, fetch = FetchType.LAZY)
    private Recipe recipe;
}
