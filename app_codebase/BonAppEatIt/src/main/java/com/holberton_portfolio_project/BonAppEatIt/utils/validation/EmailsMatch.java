package com.holberton_portfolio_project.BonAppEatIt.utils.validation;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Documented
@Constraint(validatedBy = EmailsMatchValidator.class)
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface EmailsMatch {
    String message() default "Emails do not match";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}