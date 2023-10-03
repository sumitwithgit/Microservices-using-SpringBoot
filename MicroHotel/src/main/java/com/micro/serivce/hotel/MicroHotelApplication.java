package com.micro.serivce.hotel;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class MicroHotelApplication {

	public static void main(String[] args) {
		SpringApplication.run(MicroHotelApplication.class, args);
	}

}
