package com.order;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class })
public class DEmartOrderCartWishListMicroserviceApplication {

	public static void main(String[] args) {
		SpringApplication.run(DEmartOrderCartWishListMicroserviceApplication.class, args);
	}

}
