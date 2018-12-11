package com.wz.mqtest;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@EnableEurekaServer
@SpringBootApplication
public class MqtestApplication {

	public static void main(String[] args) {
		SpringApplication.run(MqtestApplication.class, args);
	}
}
