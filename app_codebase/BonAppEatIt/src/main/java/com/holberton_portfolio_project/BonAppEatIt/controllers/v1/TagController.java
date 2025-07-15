package com.holberton_portfolio_project.BonAppEatIt.controllers.v1;

import com.holberton_portfolio_project.BonAppEatIt.constants.ApiRoutes;
import com.holberton_portfolio_project.BonAppEatIt.dto.ResponseSuccessDTO;
import com.holberton_portfolio_project.BonAppEatIt.dto.TagDTO;
import com.holberton_portfolio_project.BonAppEatIt.service.ResponseSuccessService;
import com.holberton_portfolio_project.BonAppEatIt.service.TagService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.Set;

@AllArgsConstructor
@RestController
@RequestMapping(ApiRoutes.V1.BASE + "/tags")
@ResponseStatus(HttpStatus.OK)
public class TagController {
    private final TagService tagService;
    private final ResponseSuccessService responseSuccessService;

    @GetMapping()
    public ResponseSuccessDTO getAllTags(HttpServletRequest request) {

        Set<TagDTO> tags = tagService.getAllTags();

        return responseSuccessService.createSuccessResponse(request, tags);
    }
}
