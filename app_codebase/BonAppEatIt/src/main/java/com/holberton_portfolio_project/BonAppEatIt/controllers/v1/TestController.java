package com.holberton_portfolio_project.BonAppEatIt.controllers.v1;

import com.holberton_portfolio_project.BonAppEatIt.dto.UserCreatedDTO;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@AllArgsConstructor
@RestController
@RequestMapping("/api/v1/test")
public class TestController {
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

    @PostMapping("/login")
    @ResponseStatus(HttpStatus.OK)
    public UserCreatedDTO login(Authentication authentication) {
        return UserCreatedDTO.builder()
                .email(authentication.getName())
                .build();
    }
}
