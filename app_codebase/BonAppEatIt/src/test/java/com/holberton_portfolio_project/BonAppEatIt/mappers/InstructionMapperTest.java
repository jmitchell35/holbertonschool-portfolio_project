package com.holberton_portfolio_project.BonAppEatIt.mappers;

import com.holberton_portfolio_project.BonAppEatIt.dto.InstructionOutputDTO;
import com.holberton_portfolio_project.BonAppEatIt.models.Instruction;
import org.junit.jupiter.api.Test;
import org.springframework.test.util.ReflectionTestUtils;

import java.util.*;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;

class InstructionMapperTest {
    Instruction instruction1;
    Instruction instruction2;
    Set<Instruction> testSet;

    InstructionMapper testMapper;

    InstructionMapperTest() {
        instruction1 = new Instruction(
                "test",
                1,
                any()
        );
        instruction2 = new Instruction(
                "test2",
                2,
                any()
        );
        testSet = new HashSet<>();

        testMapper = new InstructionMapper();
    }

    @Test
    void shouldOutputSimpleOutputDTO() {
        ReflectionTestUtils.setField(instruction1, "id", UUID.randomUUID());

        InstructionOutputDTO test = testMapper.toOutputDTO(instruction1);

        assertThat(test)
                .isNotNull();

        assertThat(test.getStepNumber())
                .isNotZero()
                .isEqualTo(instruction1.getStepNumber());

        assertThat(test.getText())
                .isNotNull()
                .isEqualTo(instruction1.getText());
    }

    @Test
    void shouldOutputListOfOutputDTO() {
        testSet.add(instruction1);
        testSet.add(instruction2);

        ReflectionTestUtils.setField(instruction1, "id", UUID.randomUUID());
        ReflectionTestUtils.setField(instruction2, "id", UUID.randomUUID());

        Set<InstructionOutputDTO> test = testMapper.toOutputDTO(testSet);

        assertThat(test)
                .isNotNull()
                .hasSize(2)
                .extracting(InstructionOutputDTO::getStepNumber)
                .containsExactly(instruction1.getStepNumber(), instruction2.getStepNumber());
    }
}