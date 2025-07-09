package com.holberton_portfolio_project.BonAppEatIt.service;

import com.holberton_portfolio_project.BonAppEatIt.dto.ResponseSuccessDTO;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class ResponseSuccessService {
    public ResponseSuccessDTO createSuccessResponse(HttpServletRequest request, HttpStatus status, String message, Object data) {
        // HttpStatus is type-safe, allows avoiding invalid HTTP codes
        return ResponseSuccessDTO.builder()
                .timestamp(LocalDateTime.now().toString())
                .status(status.value())
                .message(message)
                .path(request.getRequestURI())
                .data(data)
                .build();
    }

    // Convenience: default OK status with data
    public ResponseSuccessDTO createSuccessResponse(HttpServletRequest request, String message, Object data) {
        return createSuccessResponse(request, HttpStatus.OK, message, data);
    }

    // Convenience: default OK status, no data
    public ResponseSuccessDTO createSuccessResponse(HttpServletRequest request, String message) {
        return createSuccessResponse(request, HttpStatus.OK, message, null);
    }
}
