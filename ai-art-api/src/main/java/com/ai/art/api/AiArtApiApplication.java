package com.ai.art.api;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@ComponentScan(basePackages = {"com.ai.art"})
@SpringBootApplication
public class AiArtApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(AiArtApiApplication.class, args);
	}

}
