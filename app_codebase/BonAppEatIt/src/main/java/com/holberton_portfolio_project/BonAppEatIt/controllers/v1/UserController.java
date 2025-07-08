package com.holberton_portfolio_project.BonAppEatIt.controllers.v1;

import com.holberton_portfolio_project.BonAppEatIt.constants.ApiRoutes;
import com.holberton_portfolio_project.BonAppEatIt.service.UserService;

// Spring web
import org.springframework.web.bind.annotation.RequestMapping;

// http

// Jakarta

// Java utils

// Lombok
import lombok.AllArgsConstructor;


@AllArgsConstructor // For constructor injection (UserService object required)
@RequestMapping(ApiRoutes.V1.BASE + "/users")
public class UserController {

    /*
    final field means UserService object has to be created at instantiation by the compiler
    instead of field injection with @Autowired.
    The object has to be injected through a constructor (constructor injection).
    Instead of coding it we can use Lombok's AllArgsConstructor
    */
    private final UserService userService;



    //@GetMapping("/profile")
    //@ResponseStatus(HttpStatus.OK)
    //public UserProfileDTO getUserById() {
        // insert auth data extract here
        //return userService.getUserById(id);
    //}

    //@GetMapping("/{id}")
    //@ResponseStatus(HttpStatus.OK)
    //public UserLightDTO getUserById(@RequestParam UUID id) {
        //return userService.getUserById(id);
    //}

}
