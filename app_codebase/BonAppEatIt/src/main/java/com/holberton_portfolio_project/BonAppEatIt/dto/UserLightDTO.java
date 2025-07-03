package com.holberton_portfolio_project.BonAppEatIt.dto;

import com.holberton_portfolio_project.BonAppEatIt.models.Role;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

@Builder
@Getter @Setter
@AllArgsConstructor
public class UserLightDTO {
    private String email;

    @Builder.Default
    private Set<RoleUserLightNestedDTO> roles = new HashSet<>();

    private boolean isVerified;
    private boolean isActive;

    @Builder.Default
    private Set<CollectionUserLightNestedDTO> collections = new HashSet<>();
}
