package com.votes;

import com.votes.services.FileStorageProperties;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@SpringBootApplication

@EnableConfigurationProperties({ FileStorageProperties.class })
public class VotesApplication {

	public static void main(String[] args) {

		SpringApplication.run(VotesApplication.class, args);
	}

}
