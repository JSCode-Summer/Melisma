package com.summer.melisma;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing
@SpringBootApplication
public class MelismaApplication {

	public static void main(String[] args) {
		SpringApplication.run(MelismaApplication.class, args);
	}

}
