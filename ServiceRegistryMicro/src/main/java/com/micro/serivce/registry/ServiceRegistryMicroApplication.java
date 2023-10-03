package com.micro.serivce.registry;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@SpringBootApplication
@EnableEurekaServer
public class ServiceRegistryMicroApplication {

	public static void main(String[] args) {
		SpringApplication.run(ServiceRegistryMicroApplication.class, args);
	}

}
