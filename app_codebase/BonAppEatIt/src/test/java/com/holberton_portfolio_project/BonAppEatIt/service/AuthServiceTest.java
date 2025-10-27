package com.holberton_portfolio_project.BonAppEatIt.service;

import com.holberton_portfolio_project.BonAppEatIt.domain.HashedPassword;
import com.holberton_portfolio_project.BonAppEatIt.domain.PasswordPolicy;
import com.holberton_portfolio_project.BonAppEatIt.dto.UserCreatedDTO;
import com.holberton_portfolio_project.BonAppEatIt.dto.UserCreationDTO;
import com.holberton_portfolio_project.BonAppEatIt.exceptions.UserAlreadyExistsException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;
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

    private final UserCreationDTO validDto = new UserCreationDTO(
            "test@domain.de",
            "test@domain.de",
            "testuser35",
            "P@ssword12345",
            "P@ssword12345"
    );

    @Test
    void shouldRegisterNewUser() {
        // When => mock behavior
        when(mockedUserService.emailAlreadyExists(validDto.getEmail())).thenReturn(false);
        when(mockedUserService.usernameAlreadyExists(validDto.getUsername())).thenReturn(false);
        doNothing()
                .when(mockedPasswordValidationService)
                .validatePassword(validDto.getPassword(), mockedStandardPasswordPolicy);
        when(mockedPasswordEncodingService.hashPassword(validDto.getPassword()))
                .thenReturn("$2a$12$R94jzkiBohNSUQM4vua3kOC5Z7k2gnuV5n8tedUnV5hkVNgpByWP6");
        when(mockedUserService.createUser(
                eq(validDto.getEmail()),
                eq(validDto.getUsername()),
                any(HashedPassword.class)
        )).thenReturn(UserCreatedDTO.builder()
                        .username(validDto.getUsername())
                        .email(validDto.getEmail())
                        .build());

        UserCreatedDTO result = authService.registerUser(validDto);

        // Assert
        Assertions.assertEquals(validDto.getUsername(), result.getUsername());
        Assertions.assertEquals(validDto.getEmail(), result.getEmail());
    }

    @Test
    void emailShouldThrowUserAlreadyExistsException() {
        when(mockedUserService.emailAlreadyExists(validDto.getEmail())).thenReturn(true);

        UserAlreadyExistsException exception = Assertions.assertThrows(UserAlreadyExistsException.class,
                () -> authService.registerUser(validDto));

        //JUnit style
        Assertions.assertEquals("Email is already registered.",  exception.getMessage());

        // AssertJ style
        assertThatThrownBy(() -> authService.registerUser(validDto))
                .isInstanceOf(UserAlreadyExistsException.class)
                .hasMessage("Email is already registered.");

        verify(mockedUserService, never()).usernameAlreadyExists(any());
        verify(mockedPasswordValidationService, never()).validatePassword(any(), any());
        verify(mockedPasswordEncodingService, never()).hashPassword(any());
        verify(mockedUserService, never()).createUser(any(), any(), any());
    }

    @Test
    void usernameShouldThrowUserAlreadyExistsException() {
        when(mockedUserService.usernameAlreadyExists(validDto.getUsername())).thenReturn(true);

        UserAlreadyExistsException exception = Assertions.assertThrows(UserAlreadyExistsException.class,
                () -> authService.registerUser(validDto));

        // JUnit style
        Assertions.assertEquals("Username is already registered.",  exception.getMessage());

        // AssertJ style
        assertThatThrownBy(() -> authService.registerUser(validDto))
                .isInstanceOf(UserAlreadyExistsException.class)
                .hasMessage("Username is already registered.");

        verify(mockedPasswordValidationService, never()).validatePassword(any(), any());
        verify(mockedPasswordEncodingService, never()).hashPassword(any());
        verify(mockedUserService, never()).createUser(any(), any(), any());
    }
}
