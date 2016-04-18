package com.stayhungry.strategypattern.service;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ImportResource;

@SpringBootApplication
//@EnableIntegration
//@IntegrationComponentScan
@ComponentScan("com.hackathon") //Picks up everything in dependent jars
@ImportResource("classpath:bean-config.xml") 
public class ServiceApplication {
	public static void main(String[] args) throws Exception {
        SpringApplication.run(ServiceApplication.class, args);
   }
}