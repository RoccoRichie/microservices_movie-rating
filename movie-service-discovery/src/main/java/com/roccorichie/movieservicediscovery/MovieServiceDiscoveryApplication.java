package com.roccorichie.movieservicediscovery;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@SpringBootApplication
@EnableEurekaServer
public class MovieServiceDiscoveryApplication {

    public static void main(String[] args) {
        SpringApplication.run(MovieServiceDiscoveryApplication.class, args);
    }

}
