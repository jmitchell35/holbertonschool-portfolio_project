package com.holberton_portfolio_project.BonAppEatIt.mappers;

import com.holberton_portfolio_project.BonAppEatIt.dto.IngredientDTO;
import com.holberton_portfolio_project.BonAppEatIt.models.Ingredient;
import org.junit.jupiter.api.Test;
import org.springframework.test.util.ReflectionTestUtils;

import java.util.*;

import static org.assertj.core.api.Assertions.*;


class IngredientMapperTest {
    Ingredient ingredient1 = new Ingredient(
            "Singular",
            "Plural",
            true,
            true,
            true,
            true,
            true,
            true,
            true,
            true,
            true,
            true,
            true,
            true,
            new HashSet<>()
    );

    Ingredient ingredient2 = new Ingredient(
            "Singular2",
            "Plural2",
            true,
            true,
            true,
            true,
            true,
            true,
            true,
            true,
            true,
            true,
            true,
            true,
            new HashSet<>()
    );

    List<Ingredient> testList = new ArrayList<>();

    IngredientMapper mapper = new IngredientMapper();

    @Test
    void shouldOutputSimpleOutputDTO() {
        ReflectionTestUtils.setField(ingredient1, "id", UUID.randomUUID());

        IngredientDTO test = mapper.toOutputDTO(ingredient1);

        assertThat(test)
                .isNotNull();

        assertThat(test.getId())
                .isNotNull()
                .isEqualTo(ingredient1.getId());

        assertThat(test.getIngredientSingular())
                .isNotNull()
                .isEqualTo(ingredient1.getIngredientSingular());

        assertThat(test.getIngredientPlural())
                .isNotNull()
                .isEqualTo(ingredient1.getIngredientPlural());
    }

    @Test
    void shouldOutputListOfOutputDTO() {
        testList.add(ingredient1);
        testList.add(ingredient2);

        ReflectionTestUtils.setField(ingredient1, "id", UUID.randomUUID());
        ReflectionTestUtils.setField(ingredient2, "id", UUID.randomUUID());

        List<IngredientDTO> test = mapper.toOutputDTO(testList);

        assertThat(test)
                .isNotNull()
                .hasSize(2)
                .extracting(IngredientDTO::getIngredientSingular)
                .containsExactly(ingredient1.getIngredientSingular(), ingredient2.getIngredientSingular());
    }
}