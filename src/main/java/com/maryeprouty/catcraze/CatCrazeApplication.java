package com.maryeprouty.catcraze;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages={"com.maryeprouty.catcraze"})
public class CatCrazeApplication {

	public static void main(String[] args) {
		SpringApplication.run(CatCrazeApplication.class, args);
	}

}
