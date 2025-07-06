package com.holberton_portfolio_project.BonAppEatIt.exceptions;

import com.holberton_portfolio_project.BonAppEatIt.dto.ErrorDTO;
import com.holberton_portfolio_project.BonAppEatIt.dto.ErrorItemDTO;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/*
 Other option : @ControllerAdvice, compatible with traditional MVCs returning views, legacy apps

 Pros of @RestControllerAdvice :
 - Rest responses are identified by Spring and serialized
 - Clear intent, self-documenting
 - Matches @RestController at controller level
 - Consistent serialization, predictable JSON responses (no accidental HTML error pages served)
 - Future proof for Spring changes
 - API-focused
 */
@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorDTO handleValidationErrors(MethodArgumentNotValidException exception, HttpServletRequest request) {
        /*
        BindingResult is Spring's data binding and validation container
        We start by fetching this validation report object
         */
        BindingResult bindingResult = exception.getBindingResult();
        /* Each object contains :
        BindingResult {
            objectName: "userCreationDTO"
            target: UserCreationDTO@12345 (the actual object instance)

            // Error collections
            allErrors: [
                FieldError {
                    objectName: "userCreationDTO"
                    field: "email"
                    rejectedValue: "not-an-email"
                    defaultMessage: "Email must be valid"
                    codes: ["Email.userCreationDTO.email", "Email.email", "Email"]
                    bindingFailure: false
                },
                FieldError {
                    objectName: "userCreationDTO"
                    field: "password"
                    rejectedValue: "short"
                    defaultMessage: "Password should be at least 12 characters long"
                    codes: ["Length.userCreationDTO.password", "Length.password", "Length"]
                    bindingFailure: false
                },
                ObjectError {
                    objectName: "userCreationDTO"
                    defaultMessage: "Emails do not match"
                    codes: ["EmailsMatch.userCreationDTO", "EmailsMatch"]
                },
                ObjectError {
                    objectName: "userCreationDTO"
                    defaultMessage: "Passwords do not match"
                    codes: ["PasswordsMatch.userCreationDTO", "PasswordsMatch"]
                }
            ]

            fieldErrors: [
                // First two errors from allErrors (FieldError instances only)
            ]

            globalErrors: [
                // Last two errors from allErrors (ObjectError instances only)
            ]

            errorCount: 4
            fieldErrorCount: 2
            globalErrorCount: 2
            hasErrors: true
            hasFieldErrors: true
            hasGlobalErrors: true

            // Field values (including rejected ones)
            model: {
                "email": "not-an-email",
                "emailConfirmation": "different@email.com",
                "password": "short",
                "passwordConfirmation": "different-short"
            }
        }
         */

        List<ErrorItemDTO> errors = new ArrayList<>();

        // handle field errors first
        bindingResult.getFieldErrors().forEach(fieldError ->
                errors.add(ErrorItemDTO.fieldError(
                        fieldError.getDefaultMessage(),
                        fieldError.getField()
                ))
        );

        // handle cross-field errors and map them to field for frontend usage
        bindingResult.getGlobalErrors().forEach(globalError -> {
            // Need a mapping helper function (from global error to confirmation field)
            String errorField = mapGlobalErrorToField(globalError.getCode());
            if (errorField != null) {
                errors.add(ErrorItemDTO.fieldError(
                        globalError.getDefaultMessage(),
                        errorField
                ));
            } else {
                errors.add(ErrorItemDTO.businessError(globalError.getDefaultMessage()));
            }
        });

        return ErrorDTO.builder()
                .timestamp(LocalDateTime.now().toString())
                .status(400)
                .path(request.getRequestURI())
                .errors(errors)
                .build();
    }

    // Maps custom validation annotations to DTO fields
    private String mapGlobalErrorToField(String errorCode) {
        return switch (errorCode) {
            case "PasswordsMatch" -> "passwordConfirmation";
            case "EmailsMatch" -> "emailConfirmation";
            default -> null;  // Unknown global error - treat as business error
        };
    }

    @ExceptionHandler(UserAlreadyExistsException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorDTO handleUserAlreadyExists(UserAlreadyExistsException exception, HttpServletRequest request) {
        return ErrorDTO.builder()
                .timestamp(LocalDateTime.now().toString())
                .status(400)
                .path(request.getRequestURI())
                .errors(List.of(ErrorItemDTO.businessError(exception.getMessage())))
                .build();
    }
}
