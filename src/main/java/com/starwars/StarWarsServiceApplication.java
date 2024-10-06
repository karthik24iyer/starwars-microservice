package com.starwars;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@EnableCaching
public class StarWarsServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(StarWarsServiceApplication.class, args);
	}

}
