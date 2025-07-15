package com.holberton_portfolio_project.BonAppEatIt.controllers.v1;

import com.holberton_portfolio_project.BonAppEatIt.constants.ApiRoutes;
import com.holberton_portfolio_project.BonAppEatIt.dto.ResponseSuccessDTO;
import com.holberton_portfolio_project.BonAppEatIt.dto.UserCreatedDTO;
import com.holberton_portfolio_project.BonAppEatIt.dto.UserCreationDTO;
import com.holberton_portfolio_project.BonAppEatIt.service.AuthService;
import com.holberton_portfolio_project.BonAppEatIt.service.ResponseSuccessService;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@AllArgsConstructor
@RestController
@RequestMapping(ApiRoutes.V1.AUTH)
public class AuthController {
    /*
    Each request on a route requiring authentication goes through the authentication filter.
    If authentication succeeds, an Authentication object is passed on to the controller :
    {
      "message": "Authentication successful!",
      "username": "th75plu4a@mozmail.com",
      "authorities": [
        {
          "authority": "ROLE_USER"
        },
        {
          "authority": "ROLE_ADMIN"
        }
      ],
      "timestamp": "2025-01-09T10:30:45.123",
      "principal": {
        "username": "anymail@domain.com",
        "authorities": [
          {
            "authority": "ROLE_USER"
          },
          {
            "authority": "ROLE_ADMIN"
          }
        ],
        "accountNonExpired": true,
        "accountNonLocked": true,
        "credentialsNonExpired": true,
        "enabled": true
      },
      "details": {
        "remoteAddress": "127.0.0.1",
        "sessionId": "ABC123XYZ"
      },
      "authenticated": true
    }
     */

    private final AuthService authService;
    private final ResponseSuccessService responseSuccessService;

    @PostMapping("/register")
    @ResponseStatus(HttpStatus.CREATED)
    public UserCreatedDTO register(@Valid @RequestBody UserCreationDTO user) {
        return authService.registerUser(user);
    }

    @PostMapping("/login")
    @ResponseStatus(HttpStatus.OK)
    public ResponseSuccessDTO login(HttpServletRequest request, Authentication authObject) {
        return responseSuccessService.createSuccessResponse(
                request,
                "Login successful for user " + authObject.getName()
        );
    }

    @PostMapping("/logout")
    @ResponseStatus(HttpStatus.OK)
    public ResponseSuccessDTO logout(HttpServletRequest request, HttpServletResponse response) {
        HttpSession session = request.getSession(false); // don't create new session if none exist

        if (session != null) {
            session.invalidate();
        }

        // Delete client cookie
        Cookie cookie = new Cookie("JSESSIONID", null);
        cookie.setPath("/");
        cookie.setMaxAge(0);
        cookie.setHttpOnly(true);
        cookie.setSecure(true);
        response.addCookie(cookie);
        return responseSuccessService.createSuccessResponse(request, "Logged out successfully");
    }

    @GetMapping("/me")
    @ResponseStatus(HttpStatus.OK)
    public ResponseSuccessDTO getCurrentUser(HttpServletRequest request, Authentication auth) {
        if (auth != null && auth.isAuthenticated()) {
            return responseSuccessService.createSuccessResponse(
                    request,
                    "User authenticated",
                    Map.of("username", auth.getName(), "authenticated", true)
            );
        }

        return responseSuccessService.createSuccessResponse(
                request,
                "Not authenticated",
                Map.of("authenticated", false)
        );
    }
}
