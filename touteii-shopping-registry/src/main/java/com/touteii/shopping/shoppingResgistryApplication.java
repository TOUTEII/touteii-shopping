package com.touteii.shopping;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@SpringBootApplication
@EnableEurekaServer
public class shoppingResgistryApplication {
    public static void main(String[] args) {
        SpringApplication.run(shoppingResgistryApplication.class);
    }
}
