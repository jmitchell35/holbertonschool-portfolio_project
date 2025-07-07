package com.holberton_portfolio_project.BonAppEatIt.configuration;

import com.holberton_portfolio_project.BonAppEatIt.constants.ApiRoutes;

import com.holberton_portfolio_project.BonAppEatIt.security.CustomAuthenticationEntryPoint;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SpringSecurityConfig {
    private final CustomAuthenticationEntryPoint authenticationEntryPoint;

    public SpringSecurityConfig(CustomAuthenticationEntryPoint authenticationEntryPoint) {
        this.authenticationEntryPoint = authenticationEntryPoint;
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .csrf(AbstractHttpConfigurer::disable)  // No CSRF for API
                .cors(Customizer.withDefaults())  // Enable CORS for client/back interactions
                .sessionManagement(session ->
                        session.sessionCreationPolicy(SessionCreationPolicy.IF_REQUIRED))  // sessions when needed
                .httpBasic(Customizer.withDefaults())  // HTTP basic auth
                .exceptionHandling(ex ->
                        ex.authenticationEntryPoint(authenticationEntryPoint))
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers(ApiRoutes.V1.ADMIN + "/**").hasRole("ADMIN")
                        .requestMatchers(ApiRoutes.V1.AUTH + "/**").permitAll()
                        .requestMatchers(
                                HttpMethod.GET,
                                ApiRoutes.V1.RECIPES,
                                ApiRoutes.V1.RECIPES + "/{id:[0-9a-f]{8}-[0-9a-f]{4}-[0-9a-f]{4}-[0-9a-f]{4}-[0-9a-f]{12}}"
                        ).permitAll()
                        .anyRequest().authenticated()
                )
                .logout(logout -> logout
                        .logoutUrl("/api/v1/auth/logout")        // Custom logout endpoint
                        .logoutSuccessHandler((request, response, auth) -> {
                            response.setStatus(200);              // Return 200 OK
                            response.getWriter().write("{\"message\":\"Logged out successfully\"}");
                        })
                        .invalidateHttpSession(true)             // Destroy session
                        .deleteCookies("JSESSIONID")             // Remove session cookie
                );

        /*
        Spring's default User class provided
        org.springframework.security.core.userdetails.User

        It only stores:
        - String username  (usually email)
        - String password
        - Collection<GrantedAuthority> authorities
        - boolean flags (enabled, expired, etc.)

        This means that to properly store a session, one has to :
        - Tell spring how to load users from the DB (Retrieve user data for authentication/login)
        - Convert the DB's roles to Spring GrantedAuthority format (still during login process)
        - Map the User entity to Spring UserDetails interface (for proper session storage of the UserDetails object)
         */

        return http.build();
    }
}
