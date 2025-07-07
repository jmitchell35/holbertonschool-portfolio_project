package com.holberton_portfolio_project.BonAppEatIt.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.holberton_portfolio_project.BonAppEatIt.dto.ErrorDTO;
import com.holberton_portfolio_project.BonAppEatIt.dto.ErrorItemDTO;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class ErrorResponseService {
    private final ObjectMapper objectMapper;

    public  ErrorResponseService(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }

    public ErrorDTO createErrorResponse(HttpServletRequest request, HttpStatus status, List<ErrorItemDTO> errors) {
        return ErrorDTO.builder()
                .timestamp(LocalDateTime.now().toString())
                .status(status.value())
                .path(request.getRequestURI())
                .errors(errors)
                .build();
    }

    // Needed for HttpServletResponse which doesn't return automatically serializable objects
    public void writeJsonResponse(HttpServletResponse response, ErrorDTO error) throws IOException {
        /*
        IOException is a checked exception.
        This would occur at a different level than the business exceptions we have dealt with.
        It is then handled differently.
         */
        response.setContentType("application/json");
        response.setStatus(error.getStatus());

        // Convert ErrorDTO to JSON string
        String jsonResponse = objectMapper.writeValueAsString(error);

        // Write JSON to response body
        response.getWriter().write(jsonResponse);
    }
}
