package com.easybytes.easybank;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;

@SpringBootApplication
@EnableWebSecurity
@EnableMethodSecurity(jsr250Enabled = true,securedEnabled = true)
@OpenAPIDefinition(info = @Info(title = "EasyBank API", version = "1.0", description = "Documentation EasyBank API v1.0"))
public class EasybankApplication {

	public static void main(String[] args) {
		SpringApplication.run(EasybankApplication.class, args);
	}

}
