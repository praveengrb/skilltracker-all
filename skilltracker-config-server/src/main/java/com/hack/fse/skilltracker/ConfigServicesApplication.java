package com.hack.fse.skilltracker;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.config.server.EnableConfigServer;
import org.springframework.context.annotation.Configuration;


@EnableDiscoveryClient
@EnableConfigServer
@SpringBootApplication
@Configuration
@EnableAutoConfiguration
public class ConfigServicesApplication {
    public static void main(String[] args) {
        SpringApplication.run(ConfigServicesApplication.class, args);
    }
}
