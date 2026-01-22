package com.caixabank.prestamos.api;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;

@SpringBootApplication
@OpenAPIDefinition(info = @Info(title = "API Préstamos", version = "1.0", description = ""))
public class PrestamosApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(PrestamosApiApplication.class, args);
	}

}
