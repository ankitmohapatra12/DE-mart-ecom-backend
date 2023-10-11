package com.APIGateway.ApiGatewaySHOPSME.config;

import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.reactive.CorsWebFilter;
import org.springframework.web.cors.reactive.UrlBasedCorsConfigurationSource;
import org.springframework.web.reactive.config.CorsRegistry;
import org.springframework.web.reactive.config.EnableWebFlux;




@Configuration
public class ApiConfig {

	@Bean
	public RestTemplate template() {
		return new RestTemplate();
	}
	
	
	 @Bean
	    public CorsWebFilter corsWebFilter() {
	        CorsConfiguration corsConfig = new CorsConfiguration();
	        corsConfig.addAllowedOrigin("http://localhost:4200"); // Allow requests from your frontend origin
	        corsConfig.addAllowedMethod("*"); // Allow all HTTP methods (customize as needed)
	        corsConfig.addAllowedHeader("*"); // Allow all headers (customize as needed)
	       
	        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
	        source.registerCorsConfiguration("/**", corsConfig);

	        return new CorsWebFilter(source);
	    }
}
