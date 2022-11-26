package com.example.demoRest;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

@SpringBootApplication
public class DemoRestApplication extends SpringBootServletInitializer {

	public static void main(String[] args) {
		System.out.println("Hiii");
		SpringApplication.run(DemoRestApplication.class, args);
	}
	@Override
	  protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
	      return builder.sources(DemoRestApplication.class);
	  }

}
