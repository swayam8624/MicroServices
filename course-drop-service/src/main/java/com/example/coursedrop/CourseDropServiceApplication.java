package com.example.coursedrop;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class CourseDropServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(CourseDropServiceApplication.class, args);
    }

}