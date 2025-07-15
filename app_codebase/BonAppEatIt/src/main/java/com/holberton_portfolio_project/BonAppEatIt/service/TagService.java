package com.holberton_portfolio_project.BonAppEatIt.service;

import com.holberton_portfolio_project.BonAppEatIt.dto.TagDTO;
import com.holberton_portfolio_project.BonAppEatIt.mappers.TagMapper;
import com.holberton_portfolio_project.BonAppEatIt.models.Tag;
import com.holberton_portfolio_project.BonAppEatIt.repository.TagRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

@AllArgsConstructor
@Service
public class TagService {
    private final TagRepository tagRepository;
    private final TagMapper tagMapper;

    public Set<TagDTO> getAllTags() {

        List<Tag> tags = tagRepository.findAll();

        return tagMapper.toTagDTO(tags);
    }
}
