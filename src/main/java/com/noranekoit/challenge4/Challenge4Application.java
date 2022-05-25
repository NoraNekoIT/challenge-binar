package com.noranekoit.challenge4;


import io.jsonwebtoken.Jwts;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeIn;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.security.SecurityScheme;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;


@SpringBootApplication
@EntityScan(basePackages = {"com.noranekoit.challenge4.models.entity"})
@OpenAPIDefinition(info = @Info(title = "Ticket Film API",
		version = "1.0",
		description = "API Information by Antonius Bun Wijaya"))
public class Challenge4Application implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(Challenge4Application.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		System.out.println("test api");
	}
}
