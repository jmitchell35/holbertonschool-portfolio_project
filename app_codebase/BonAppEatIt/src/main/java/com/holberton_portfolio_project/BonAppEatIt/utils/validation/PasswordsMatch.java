package com.holberton_portfolio_project.BonAppEatIt.utils.validation;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;
import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Documented // Connection to Javadoc
// "@Constraint contract" : requires implementing the three methods below
@Constraint(validatedBy = PasswordsMatchValidator.class)  // Link to validator logic
@Target({ElementType.TYPE})  // Can be applied to a class of object (no field or method)
@Retention(RetentionPolicy.RUNTIME)  // Available for use at runtime
public @interface PasswordsMatch {

    // These three methods are REQUIRED by Bean Validation spec for @Constraint annotation
    String message() default "Passwords do not match";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}