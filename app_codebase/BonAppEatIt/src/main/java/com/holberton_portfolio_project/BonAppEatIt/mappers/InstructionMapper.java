package com.holberton_portfolio_project.BonAppEatIt.mappers;

import com.holberton_portfolio_project.BonAppEatIt.dto.InstructionOutputDTO;
import com.holberton_portfolio_project.BonAppEatIt.models.Instruction;
import org.springframework.stereotype.Component;

import java.util.Set;
import java.util.stream.Collectors;

@Component
public class InstructionMapper {

    public InstructionOutputDTO toOutputDTO(Instruction instruction) {
        return InstructionOutputDTO.builder()
                .text(instruction.getText())
                .stepNumber(instruction.getStepNumber())
                .build();
    }

    public Set<InstructionOutputDTO> toOutputDTO(Set<Instruction> instructions) {
        return instructions.stream()
                .map(this::toOutputDTO)
                .collect(Collectors.toSet());
    }
}