package com.example.docker.spring.cloud.netflix.DockerNetflixEurekaServer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@EnableEurekaServer
@SpringBootApplication
public class DockerNetflixEurekaServerApplication {

	public static void main(String[] args) {
		SpringApplication.run(DockerNetflixEurekaServerApplication.class, args);
	}

}
