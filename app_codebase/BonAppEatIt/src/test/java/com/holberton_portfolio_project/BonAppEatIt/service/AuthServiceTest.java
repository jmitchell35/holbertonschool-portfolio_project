package com.holberton_portfolio_project.BonAppEatIt.service;

import com.holberton_portfolio_project.BonAppEatIt.domain.HashedPassword;
import com.holberton_portfolio_project.BonAppEatIt.domain.PasswordPolicy;
import com.holberton_portfolio_project.BonAppEatIt.dto.UserCreatedDTO;
import com.holberton_portfolio_project.BonAppEatIt.dto.UserCreationDTO;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class AuthServiceTest {
    @Mock
    private UserService mockedUserService;

    @Mock
    private final PasswordValidationService mockedPasswordValidationService = mock(PasswordValidationService.class);

    @Mock
    private final PasswordEncodingService mockedPasswordEncodingService = mock(PasswordEncodingService.class);

    @Mock
    private final PasswordPolicy mockedStandardPasswordPolicy = mock(PasswordPolicy.class);

    @InjectMocks  // This injects all the mocks above into AuthService
    private AuthService authService;

    @Test
    void shouldRegisterNewUser() {
        // Given => provide test data
        UserCreationDTO dto = new UserCreationDTO(
                "test@domain.de",
                "test@domain.de",
                "testuser35",
                "P@ssword12345",
                "P@ssword12345"
        );

        // When => mock behavior
        when(mockedUserService.emailAlreadyExists(dto.getEmail())).thenReturn(false);
        when(mockedUserService.usernameAlreadyExists(dto.getUsername())).thenReturn(false);
        doNothing()
                .when(mockedPasswordValidationService)
                .validatePassword(dto.getPassword(), mockedStandardPasswordPolicy);
        when(mockedPasswordEncodingService.hashPassword(dto.getPassword()))
                .thenReturn("$2a$12$R94jzkiBohNSUQM4vua3kOC5Z7k2gnuV5n8tedUnV5hkVNgpByWP6");
        when(mockedUserService.createUser(
                eq(dto.getEmail()),
                eq(dto.getUsername()),
                any(HashedPassword.class)
        )).thenReturn(UserCreatedDTO.builder()
                        .username(dto.getUsername())
                        .email(dto.getEmail())
                        .build());

        UserCreatedDTO result = authService.registerUser(dto);

        Assertions.assertEquals(dto.getUsername(), result.getUsername());
        Assertions.assertEquals(dto.getEmail(), result.getEmail());
    }
}
