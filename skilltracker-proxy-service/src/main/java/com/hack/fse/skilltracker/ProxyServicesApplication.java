package com.hack.fse.skilltracker;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import org.springframework.cloud.netflix.zuul.EnableZuulProxy;


@SpringBootApplication
@EnableZuulProxy
public class ProxyServicesApplication {

    public static void main(String[] args) {
        SpringApplication.run(ProxyServicesApplication.class, args);
    }
}