package com.holberton_portfolio_project.BonAppEatIt.service;

import com.holberton_portfolio_project.BonAppEatIt.models.Unit;
import com.holberton_portfolio_project.BonAppEatIt.repository.UnitRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@AllArgsConstructor
@Service
public class UnitService {
    private final UnitRepository unitRepository;

    public List<Unit> getAllUnits() {
        return unitRepository.findAll();
    }
}
