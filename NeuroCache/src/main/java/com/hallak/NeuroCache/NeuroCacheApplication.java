package com.hallak.NeuroCache;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class NeuroCacheApplication {

	public static void main(String[] args) {
		SpringApplication.run(NeuroCacheApplication.class, args);
	}

}
