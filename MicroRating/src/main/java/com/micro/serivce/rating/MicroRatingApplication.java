package com.micro.serivce.rating;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class MicroRatingApplication {

	public static void main(String[] args) {
		SpringApplication.run(MicroRatingApplication.class, args);
	}
	
}
