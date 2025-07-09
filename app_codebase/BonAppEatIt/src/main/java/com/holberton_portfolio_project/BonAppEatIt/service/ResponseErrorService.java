package com.holberton_portfolio_project.BonAppEatIt.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.holberton_portfolio_project.BonAppEatIt.dto.ResponseErrorDTO;
import com.holberton_portfolio_project.BonAppEatIt.dto.ResponseErrorItemDTO;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class ResponseErrorService {
    private final ObjectMapper objectMapper;

    public ResponseErrorService(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }

    public ResponseErrorDTO createErrorResponse(HttpServletRequest request, HttpStatus status, List<ResponseErrorItemDTO> errors) {
        return ResponseErrorDTO.builder()
                .timestamp(LocalDateTime.now().toString())
                .status(status.value())
                .path(request.getRequestURI())
                .errors(errors)
                .build();
    }

    // Needed for HttpServletResponse which doesn't return automatically serializable objects
    public void writeJsonResponse(HttpServletResponse response, ResponseErrorDTO error) throws IOException {
        /*
        IOException is a checked exception.
        This would occur at a different level than the business exceptions we have dealt with.
        It is then handled differently.
         */
        response.setContentType("application/json");
        response.setStatus(error.getStatus());

        // Convert ResponseErrorDTO to JSON string
        String jsonResponse = objectMapper.writeValueAsString(error);

        // Write JSON to response body
        response.getWriter().write(jsonResponse);
    }
}
