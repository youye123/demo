package com.gd.demo;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Configuration;

@SpringBootApplication
@Configuration
public class DemoCommonApplication {

    public static void main(String[] args) {
        SpringApplication.run(DemoCommonApplication.class, args);
    }
}
