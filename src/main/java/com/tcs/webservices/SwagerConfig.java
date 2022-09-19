package com.tcs.webservices;

import org.springdoc.core.GroupedOpenApi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;

public class SwagerConfig {

	@Configuration
	@OpenAPIDefinition(info = @Info(title = "Spring Data JPA Demo for Social Media App", 
	description = "Developing Ecommerce App", 
	contact = @Contact(name = "Enrique Felix", email = "felix.e@tcs.com"),
	version = "1.0"))
	public class SwaggerConfig {

		@Bean
		public GroupedOpenApi api() {
			return GroupedOpenApi.builder()
					.group("default")
					.packagesToScan("com.tcs.webservices.controller")
					.build();
		}
	}
}
