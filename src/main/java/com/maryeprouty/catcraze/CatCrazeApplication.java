package com.maryeprouty.catcraze;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication(scanBasePackages={"com.maryeprouty.catcraze"})
@EnableScheduling
public class CatCrazeApplication {

	public static void main(String[] args) {
		SpringApplication.run(CatCrazeApplication.class, args);
	}

}
