package com.api.TechLearnAPI;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class TechLearnApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(TechLearnApiApplication.class, args);
	}

}
