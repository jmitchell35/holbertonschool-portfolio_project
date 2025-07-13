package com.holberton_portfolio_project.BonAppEatIt.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.util.UUID;

@Builder
@Getter
@AllArgsConstructor
public class TagDTO {
    private UUID id;
    private String name;
    private String colorHex;
}
