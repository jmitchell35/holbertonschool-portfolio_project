package com.holberton_portfolio_project.BonAppEatIt.service;

import com.holberton_portfolio_project.BonAppEatIt.dto.ErrorDTO;
import com.holberton_portfolio_project.BonAppEatIt.dto.ErrorItemDTO;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class ErrorResponseService {
    public ErrorDTO createErrorResponse(HttpServletRequest request, HttpStatus status, List<ErrorItemDTO> errors) {
        return ErrorDTO.builder()
                .timestamp(LocalDateTime.now().toString())
                .status(status.value())
                .path(request.getRequestURI())
                .errors(errors)
                .build();
    }
}
