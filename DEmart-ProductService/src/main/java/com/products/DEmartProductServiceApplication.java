package com.products;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

import com.products.externalservices.BrandService;

@EnableDiscoveryClient
@SpringBootApplication
@EnableFeignClients
public class DEmartProductServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(DEmartProductServiceApplication.class, args);
	}
	
	
	

}
