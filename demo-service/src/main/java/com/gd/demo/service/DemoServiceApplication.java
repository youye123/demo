package com.gd.demo.service;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.Configuration;

@SpringBootApplication
@Configuration
@EnableEurekaClient
public class DemoServiceApplication {


    public static void main(String[] args) {
        SpringApplication.run(DemoServiceApplication.class, args);
    }
}
