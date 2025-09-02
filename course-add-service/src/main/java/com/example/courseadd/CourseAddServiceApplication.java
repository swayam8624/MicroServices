package com.example.courseadd;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class CourseAddServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(CourseAddServiceApplication.class, args);
	}

}