package com.holberton_portfolio_project.BonAppEatIt.service;

import com.holberton_portfolio_project.BonAppEatIt.models.Tag;
import com.holberton_portfolio_project.BonAppEatIt.repository.TagRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@AllArgsConstructor
@Service
public class TagService {
    private final TagRepository tagRepository;

    public List<Tag> getAllTags() {
        return tagRepository.findAll();
    }
}
