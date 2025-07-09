package com.holberton_portfolio_project.BonAppEatIt.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ResponseErrorItemDTO {
    private String message;
    private String field;

    // "Convenience" constructors :
    public ResponseErrorItemDTO(String message) {
        this.message = message;
        this.field = null;
    }

    public static ResponseErrorItemDTO businessError(String message) {
        return new ResponseErrorItemDTO(message);
    }

    public static ResponseErrorItemDTO fieldError(String message, String field) {
        return new ResponseErrorItemDTO(message, field);
    }
}
