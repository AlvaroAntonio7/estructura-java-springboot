package com.example.rent_a_car;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpMethod;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@SpringBootApplication
public class RentACarApplication {

	public static void main(String[] args) {
		SpringApplication.run(RentACarApplication.class, args);
	}


	//Para configurar GLOBALMENTE politica de CORS
	/*
	@Bean
	public WebMvcConfigurer corsConfigurer(){
		return new WebMvcConfigurer() {
			@Override
			public void addCorsMappings(CorsRegistry registry) {
				registry.addMapping("/**").allowedOrigins("*").allowedMethods("*").allowedHeaders("*");
				// el /**indica todas las rutas, se puede colocar especificas
			    //Tambien puede usarse api/ruta/**
				//* hace referencia a todas las rutas, pero puede usarse una especifica "http://localhost:4200"
				// en allowedMethods puede ir "GET", "POST", etc, * hace referencia a todos los metododos
			}
		};
	}

	 */
}


