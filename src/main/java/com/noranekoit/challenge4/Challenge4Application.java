package com.noranekoit.challenge4;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
	private static final Logger LOG= LoggerFactory.getLogger(Challenge4Application.class);
	public static void main(String[] args) {
		SpringApplication.run(Challenge4Application.class, args);
	}

	@Override
	public void run(String... args) {
		LOG.info("test run");
	}
}
