package com.holberton_portfolio_project.BonAppEatIt.mappers;

import com.holberton_portfolio_project.BonAppEatIt.dto.TagDTO;
import com.holberton_portfolio_project.BonAppEatIt.models.Tag;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Component
public class TagMapper {
    public TagDTO toTagDTO(Tag tag) {
        return TagDTO.builder()
                .id(tag.getId())
                .name(tag.getName())
                .backgroundColorHex(tag.getBackgroundColorHex())
                .fontColorHex(tag.getFontColorHex())
                .build();
    }

    public Set<TagDTO> toTagDTO(Set<Tag> tags) {
        return tags.stream()
                .map(this::toTagDTO)  // Method reference
                .collect(Collectors.toSet());
    }

    public Set<TagDTO> toTagDTO(List<Tag> tags) {
        return tags.stream()
                .map(this::toTagDTO)  // Method reference
                .collect(Collectors.toSet());
    }
}
