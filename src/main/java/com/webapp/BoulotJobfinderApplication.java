package com.webapp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

@SpringBootApplication
//		(exclude = {DataSourceAutoConfiguration.class })
public class BoulotJobfinderApplication {

	public static void main(String[] args) {
		SpringApplication.run(BoulotJobfinderApplication.class, args);
	}

}
