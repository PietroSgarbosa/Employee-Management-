package com.employeemanagement.employeemanagement.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.models.ExternalDocumentation;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;

@Configuration
public class SwaggerConfig {

	@Bean
	OpenAPI openAPI() {
		return new OpenAPI()
				.info(new Info().title("EMPLOYEE MANAGEMENT - Rest API")
						.description("API para gerenciar cursos de colaboradores no âmbito empresarial").version("1.0")
						.termsOfService("Termo de uso: Direitos reservados para Simples")
						.license(new License().name("Simples").url("https://simples.software/")))
				.externalDocs(new ExternalDocumentation().description("Pietro Sgarbosa")
						.url("https://github.com/PietroSgarbosa"));
	}

}
