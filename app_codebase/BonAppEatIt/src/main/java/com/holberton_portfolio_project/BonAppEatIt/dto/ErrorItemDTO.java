package com.holberton_portfolio_project.BonAppEatIt.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ErrorItemDTO {
    private String message;
    private String field;

    // "Convenience" constructors :
    public ErrorItemDTO(String message) {
        this.message = message;
        this.field = null;
    }

    public static ErrorItemDTO businessError(String message) {
        return new ErrorItemDTO(message);
    }

    public static ErrorItemDTO fieldError(String message, String field) {
        return new ErrorItemDTO(message, field);
    }
}
