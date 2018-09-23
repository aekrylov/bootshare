package com.github.aekrylov.bootshare;

import org.hashids.Hashids;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class BootshareApplication {

	public static void main(String[] args) {
		SpringApplication.run(BootshareApplication.class, args);
	}

	@Bean
	public Hashids hashids() {
	    return new Hashids("q7cebo3nc2", 3, "abcdefghijklmnopqrstuvwxyz1234567890");
    }
}
