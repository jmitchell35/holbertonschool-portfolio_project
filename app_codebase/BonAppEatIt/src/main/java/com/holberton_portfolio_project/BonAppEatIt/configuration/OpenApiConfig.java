package com.holberton_portfolio_project.BonAppEatIt.configuration;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.servers.Server;
import org.springframework.context.annotation.Configuration;

@OpenAPIDefinition(
        info = @Info(
                title = "BonAppEatIt API",
                version = "1.0",
                description = "Recipe management API for Holberton portfolio project"
        ),
        servers = @Server(url = "http://localhost:8080", description = "Development server")
)
@Configuration
public class OpenApiConfig {
}
