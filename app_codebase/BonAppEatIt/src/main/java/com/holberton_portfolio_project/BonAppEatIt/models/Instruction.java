package com.holberton_portfolio_project.BonAppEatIt.models;

// jakarta.persistence
import jakarta.persistence.Entity;
import jakarta.persistence.Column;
import jakarta.persistence.EntityListeners;


// org.springframework.data
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

// Lombok boilerplate methods
import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import lombok.Builder;


@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@EntityListeners(AuditingEntityListener.class)
public class Instruction extends BaseEntity {}
