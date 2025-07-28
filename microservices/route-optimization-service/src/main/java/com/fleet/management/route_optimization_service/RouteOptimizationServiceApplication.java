package com.fleet.management.route_optimization_service;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class RouteOptimizationServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(RouteOptimizationServiceApplication.class, args);
	}

}
