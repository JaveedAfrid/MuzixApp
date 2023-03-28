package com.learn.apigatewayservice;

import com.learn.apigatewayservice.filters.JWTValidationFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.reactive.CorsWebFilter;
import org.springframework.web.cors.reactive.UrlBasedCorsConfigurationSource;


import java.util.Arrays;
import java.util.Collections;

@SpringBootApplication
@EnableDiscoveryClient
public class ApiGatewayServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(ApiGatewayServiceApplication.class, args);
	}

	@Autowired
	private JWTValidationFilter jwtValidationFilter;

	@Bean
	public CorsWebFilter corsWebFilter() {

		final CorsConfiguration corsConfig = new CorsConfiguration();
		corsConfig.setAllowedOrigins(Collections.singletonList("*"));
		corsConfig.setMaxAge(3600L);
		corsConfig.setAllowedMethods(Arrays.asList("GET", "POST", "PUT", "DELETE"));
		corsConfig.addAllowedHeader("*");

		final UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
		source.registerCorsConfiguration("/**", corsConfig);

		return new CorsWebFilter(source);
	}


	@Bean
	public RouteLocator apiRoutes(RouteLocatorBuilder builder){

		return builder.routes()
				.route("favourites_route", route ->
						route.path("/api/v1/favourites/**")
								.filters(fiterspec -> fiterspec.filter(jwtValidationFilter))
								.uri("lb://favourites-services"))
				.route("recommendation_route", route ->
						route.path("/api/v1/recommend/**")
								.filters(fiterspec -> fiterspec.filter(jwtValidationFilter))
								.uri("lb://recommendation-service")
				)
				.route("user_route", route ->
						route.path("/api/v1/user/**")
								.uri("lb://user-service")
				)
				.build();
	}



}
