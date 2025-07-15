package com.holberton_portfolio_project.BonAppEatIt.controllers.v1;

import com.holberton_portfolio_project.BonAppEatIt.constants.ApiRoutes;
import com.holberton_portfolio_project.BonAppEatIt.dto.ResponseSuccessDTO;
import com.holberton_portfolio_project.BonAppEatIt.dto.UnitDTO;
import com.holberton_portfolio_project.BonAppEatIt.service.ResponseSuccessService;
import com.holberton_portfolio_project.BonAppEatIt.service.UnitService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@AllArgsConstructor
@RestController
@ResponseStatus(HttpStatus.OK)
@RequestMapping(ApiRoutes.V1.BASE + "/units")
public class UnitController {
    private final UnitService unitService;
    private final ResponseSuccessService responseSuccessService;

    @GetMapping()
    public ResponseSuccessDTO getAllUnits(HttpServletRequest request) {

        List<UnitDTO> tags = unitService.getAllUnits();

        return responseSuccessService.createSuccessResponse(request, tags);
    }
}
