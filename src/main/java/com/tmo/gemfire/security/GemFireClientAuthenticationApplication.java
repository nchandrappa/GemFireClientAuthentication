package com.tmo.gemfire.security;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ImportResource;

@SpringBootApplication
@ImportResource(value="tmoClientCache.xml")
public class GemFireClientAuthenticationApplication {

	public static void main(String[] args) {
		SpringApplication.run(GemFireClientAuthenticationApplication.class, args);
	}
}
