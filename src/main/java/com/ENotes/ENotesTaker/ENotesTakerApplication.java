package com.ENotes.ENotesTaker;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class ENotesTakerApplication {

	public static void main(String[] args) {
		SpringApplication.run(ENotesTakerApplication.class, args);
	}

}
