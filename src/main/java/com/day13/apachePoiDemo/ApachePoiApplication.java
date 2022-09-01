package com.day13.apachePoiDemo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class ApachePoiApplication {

	public static void main(String[] args) {
		SpringApplication.run(ApachePoiApplication.class, args);
	}

}
