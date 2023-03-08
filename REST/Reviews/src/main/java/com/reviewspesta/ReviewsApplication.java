package com.reviewspesta;

import com.reviewspesta.services.FileStorageProperties;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@SpringBootApplication

@EnableConfigurationProperties({FileStorageProperties.class})
public class ReviewsApplication {

	public static void main(String[] args) {

		SpringApplication.run(ReviewsApplication.class, args);
	}

}
