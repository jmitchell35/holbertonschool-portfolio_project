package com.holberton_portfolio_project.BonAppEatIt.exceptions;

public class RecipeNotFoundException extends RuntimeException {
    public RecipeNotFoundException(String message) {
        super(message);
    }
}