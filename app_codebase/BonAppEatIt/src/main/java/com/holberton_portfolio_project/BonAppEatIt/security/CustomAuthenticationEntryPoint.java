package com.holberton_portfolio_project.BonAppEatIt.security;

import com.holberton_portfolio_project.BonAppEatIt.dto.ResponseErrorDTO;
import com.holberton_portfolio_project.BonAppEatIt.dto.ResponseErrorItemDTO;
import com.holberton_portfolio_project.BonAppEatIt.service.ResponseErrorService;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;

@Component
public class CustomAuthenticationEntryPoint implements AuthenticationEntryPoint {
    private final ResponseErrorService responseErrorService;

    public CustomAuthenticationEntryPoint(ResponseErrorService responseErrorService) {
        this.responseErrorService = responseErrorService;
    }

    @SuppressWarnings("RedundantThrows")
    @Override
    public void commence(
            HttpServletRequest request,
            HttpServletResponse response,
            AuthenticationException authException) throws IOException, ServletException {
        /*
        HttpServletResponse doesn't handle conversion to JSON => needs writeJsonResponse from errorResponseService
        The Security Filter related to Authentication actually intervenes before the request reaches the controller
        level.
        Request → Security Filters → Controllers → Services
                    ↑                     ↑
            AuthenticationEntryPoint     GlobalExceptionHandler
            operates here               catches exceptions from
                                       Controllers + Services

        Request Flow:
        1. Security Filters (AuthenticationEntryPoint handles failures here)
        2. Controllers (GlobalExceptionHandler catches exceptions from here)
        3. Services (GlobalExceptionHandler also catches exceptions from here)

        And in the Spring Security ecosystem :

        Request → FilterChainProxy → [Authentication Filters] → Authorization → Controller
                                              ↑
                                    AuthenticationEntryPoint triggered here

         */

        ResponseErrorDTO error = ResponseErrorDTO.builder()
                .timestamp(LocalDateTime.now().toString())
                .status(HttpStatus.UNAUTHORIZED.value())
                .path(request.getRequestURI())
                .errors(List.of(ResponseErrorItemDTO.businessError("Authentication required to access this resource")))
                .build();

        // Write JSON response
        responseErrorService.writeJsonResponse(response, error);
    }
}
