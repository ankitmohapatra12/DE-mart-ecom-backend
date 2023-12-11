package com.review;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class })
public class DEmartRatingReviewMicroserviceApplication {

	public static void main(String[] args) {
		SpringApplication.run(DEmartRatingReviewMicroserviceApplication.class, args);
	}

}
