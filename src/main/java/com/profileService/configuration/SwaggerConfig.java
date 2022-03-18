package com.profileService.configuration;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;




@Configuration

public class SwaggerConfig {
	
	
	@Bean
	public OpenAPI directorOpenAPI() {
		return new OpenAPI().info(new Info().title("ProfileSevice")
				.description("APIS de perfilamiento y login")
				.version("0.0.1-SNAPSHOT")
				);
	}

}