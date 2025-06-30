package com.holberton_portfolio_project.BonAppEatIt.configuration;

import com.holberton_portfolio_project.BonAppEatIt.constants.ApiRoutes;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.LogoutConfigurer;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SpringSecurityConfig {

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
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
                .formLogin(form -> form
                        .loginPage("/login")
                        .permitAll()
                )
                .logout(LogoutConfigurer::permitAll);

        return http.build();
    }
}
