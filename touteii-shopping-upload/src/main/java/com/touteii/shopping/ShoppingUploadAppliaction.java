package com.touteii.shopping;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;



@SpringBootApplication
@EnableDiscoveryClient
public class ShoppingUploadAppliaction {

    public static void main(String[] args) {
        SpringApplication.run(ShoppingUploadAppliaction.class);
    }

}
