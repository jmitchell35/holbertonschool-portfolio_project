package com.holberton_portfolio_project.BonAppEatIt.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Builder
@Getter @Setter
@AllArgsConstructor // Full constructor
@NoArgsConstructor // For Jackson,Spring
public class ErrorDTO {
    private String timestamp;
    private String path;
    private int status;

    @Builder.Default
    private List<ErrorItemDTO> errors = new ArrayList<>();
}
