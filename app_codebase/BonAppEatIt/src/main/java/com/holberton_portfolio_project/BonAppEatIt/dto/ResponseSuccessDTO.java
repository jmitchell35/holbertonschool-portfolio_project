package com.holberton_portfolio_project.BonAppEatIt.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Builder
@Getter @Setter
@AllArgsConstructor // Full constructor
@NoArgsConstructor // For Jackson,Spring
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ResponseSuccessDTO {
    private String timestamp;
    private String path;

    @Builder.Default
    private int status = 200;

    @Builder.Default
    private String message = "Success";

    private Object data;
}
