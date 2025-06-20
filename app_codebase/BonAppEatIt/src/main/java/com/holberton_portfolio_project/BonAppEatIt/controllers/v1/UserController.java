package com.holberton_portfolio_project.BonAppEatIt.controllers.v1;

import com.holberton_portfolio_project.BonAppEatIt.dto.UserCreationModel;
import com.holberton_portfolio_project.BonAppEatIt.dto.UserResponseModel;

import com.holberton_portfolio_project.BonAppEatIt.service.UserService;

// Spring web
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

// http
import org.springframework.http.HttpStatus;

// Jakarta
import jakarta.validation.Valid;

// Java utils
import java.util.UUID;

// Lombok
import lombok.AllArgsConstructor;


@AllArgsConstructor // For constructor injection (UserService object required)
@RequestMapping("/users")
@ResponseStatus(HttpStatus.CREATED)
public class UserController extends BaseV1Controller {

    /*
    final field means UserService object has to be created at instantiation by the compiler
    instead of field injection with @Autowired.
    The object has to be injected through a constructor (constructor injection).
    Instead of coding it we can use Lombok's AllArgsConstructor
    */
    private final UserService userService;

    @PostMapping
    @RequestMapping("/register")
    public UserResponseModel register(@Valid @RequestBody UserCreationModel user) {
        return userService.createUser(user);
    }

    @GetMapping
    @RequestMapping("/profile")
    public UserResponseModel getUserById() {
        // insert auth data extract here
        return userService.getUserById(id);
    }

    @GetMapping
    @RequestMapping("/{id}")
    public UserResponseModel getUserById(@RequestParam UUID id) {
        return userService.getUserById(id);
    }

}
