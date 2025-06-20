package com.holberton_portfolio_project.BonAppEatIt;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

// Enables entities lifecycle events watch
@EnableJpaAuditing
@SpringBootApplication
public class BonAppEatItApplication {

	public static void main(String[] args) {
		SpringApplication.run(BonAppEatItApplication.class, args);
	}

}
