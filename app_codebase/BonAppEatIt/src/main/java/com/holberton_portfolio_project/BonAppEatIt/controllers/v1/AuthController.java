package com.holberton_portfolio_project.BonAppEatIt.controllers.v1;

import com.holberton_portfolio_project.BonAppEatIt.constants.ApiRoutes;
import com.holberton_portfolio_project.BonAppEatIt.dto.UserCreatedDTO;
import com.holberton_portfolio_project.BonAppEatIt.dto.UserCreationDTO;
import com.holberton_portfolio_project.BonAppEatIt.service.AuthService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@AllArgsConstructor
@RestController
@RequestMapping(ApiRoutes.V1.AUTH)
public class AuthController {

    private final AuthService authService;

    @PostMapping("/register")
    @ResponseStatus(HttpStatus.CREATED)
    public UserCreatedDTO register(@Valid @RequestBody UserCreationDTO user) {
        return authService.registerUser(user);
    }

    //@PostMapping("/login")
    //@ResponseStatus(HttpStatus.OK)
    //public void loginUser(@Valid @RequestBody UserLoginDTO user) {
        //return authService.loginUser(user);
    //}
}
