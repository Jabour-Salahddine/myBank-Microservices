package com.mybank.gatewayserver;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;

import java.time.LocalDateTime;

@SpringBootApplication
public class GatewayserverApplication {

	public static void main(String[] args) {
		SpringApplication.run(GatewayserverApplication.class, args);
	}


    // this methode is used to configure our chosen routes for the gateway server. we used predifined predicates and filters
	@Bean
	public RouteLocator myBankRouteConfig(RouteLocatorBuilder routeLocatorBuilder) {
		return routeLocatorBuilder.routes()
				.route(p -> p
						.path("/mybank/accounts/**")
						.filters( f -> f.rewritePath("/mybank/accounts/(?<segment>.*)","/${segment}")
						.addResponseHeader("X-ResponseTime", LocalDateTime.now().toString())
						.circuitBreaker(c -> c.setName("CircuitBreakerForAccounts")
										.setFallbackUri("forward:/accountsSupportTeam")))
						.uri("lb://ACCOUNTS"))

				.route(p -> p
						.path("/mybank/loans/**")
						.filters( f -> f.rewritePath("/mybank/loans/(?<segment>.*)","/${segment}")
						.addResponseHeader("X-ResponseTime", LocalDateTime.now().toString()))
						.uri("lb://LOANS"))

				.route(p -> p
						.path("/mybank/cards/**")
						.filters( f -> f.rewritePath("/mybank/cards/(?<segment>.*)","/${segment}")
								.addResponseHeader("X-ResponseTime", LocalDateTime.now().toString()))
						.uri("lb://CARDS")).build();


	}




}
