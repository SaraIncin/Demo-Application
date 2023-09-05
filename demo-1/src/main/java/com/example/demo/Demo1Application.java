package com.example.demo;

import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@SpringBootApplication
public class Demo1Application {
	 private static final Logger logger = LoggerFactory.getLogger(Demo1Application.class);

	@Bean
	public ModelMapper modelMapper() {
	    return new ModelMapper();
	}
	public static void main(String[] args) {
		logger.info("Starting Demo Application");
		SpringApplication.run(Demo1Application.class, args);
	}

}
