package com.learn.muzixlogin;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
@EnableDiscoveryClient
@EnableSwagger2
public class MuzixLoginApplication {

	public static void main(String[] args) {
		SpringApplication.run(MuzixLoginApplication.class, args);
	}

}
