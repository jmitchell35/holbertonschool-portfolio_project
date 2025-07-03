package com.holberton_portfolio_project.BonAppEatIt.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

@Builder
@Getter @Setter
@AllArgsConstructor
public class UserProfileDTO {
    private String email;
    private boolean isVerified;
    private Set<CollectionUserProfileDTO> collections = new HashSet<>();
}
