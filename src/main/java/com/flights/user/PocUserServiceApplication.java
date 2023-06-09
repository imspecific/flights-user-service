package com.flights.user;

import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;


@Slf4j
@SpringBootApplication
@EnableEurekaClient
public class PocUserServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(PocUserServiceApplication.class, args);
		log.info("POC-User Service is running..");
	}

}
