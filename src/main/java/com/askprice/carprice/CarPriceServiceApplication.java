package com.askprice.carprice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
public class CarPriceServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(CarPriceServiceApplication.class, args);
	}
}
