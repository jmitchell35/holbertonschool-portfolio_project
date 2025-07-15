package com.holberton_portfolio_project.BonAppEatIt.mappers;

import com.holberton_portfolio_project.BonAppEatIt.dto.UnitDTO;
import com.holberton_portfolio_project.BonAppEatIt.models.Unit;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class UnitMapper {
    public UnitDTO toOutputDTO(Unit unit) {
        return UnitDTO.builder()
                .id(unit.getId())
                .name(unit.getName())
                .build();
    }

    public List<UnitDTO> toOutputDTO(List<Unit> units) {
        return units.stream()
                .map(this::toOutputDTO)
                .collect(Collectors.toList());
    }
}
