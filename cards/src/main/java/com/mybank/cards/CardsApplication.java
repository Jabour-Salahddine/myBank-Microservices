package com.mybank.cards;

import io.swagger.v3.oas.annotations.ExternalDocumentation;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.info.License;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing(auditorAwareRef = "AuditAware") // Cette annotation active l'audit JPA, ce qui permet de suivre les modifications des entités, et spécifie le bean "AuditAware" pour fournir les informations sur l'auditeur
@OpenAPIDefinition(
		info = @Info(
				title = "Cards microservice REST API Documentation",
				description = "MyBank Cards microservice REST API Documentation",
				version = "v0.1",
				contact = @Contact(
						name = "jabour salahddine",
						email = "jaboursalahddine@gmail.com",
						url = "https://github.com/Jabour-Salahddine?tab=repositories"
				),
				license = @License(
						name = "Spring Boot 3.5.3",
						url = "https://start.spring.io/"
				)
		),
		externalDocs = @ExternalDocumentation(
				description =  "MyBank Cards microservice REST API Documentation",
				url = "http://localhost:8080/docs"
		)
)

public class CardsApplication {

	public static void main(String[] args) {
		SpringApplication.run(CardsApplication.class, args);
	}

}
