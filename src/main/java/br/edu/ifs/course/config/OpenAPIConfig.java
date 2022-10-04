package br.edu.ifs.course.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;

@Configuration
public class OpenAPIConfig {

	@Bean
	public OpenAPI springSalesOpenAPI() {
		return new OpenAPI()
				.info(new Info().title("Sales API")
			              .description("API for managing a sales webapplication.")
			              .version("v0.0.1"));
	}
}
