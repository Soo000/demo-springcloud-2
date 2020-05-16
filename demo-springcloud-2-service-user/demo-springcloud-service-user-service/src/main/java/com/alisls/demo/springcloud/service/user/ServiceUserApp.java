package com.alisls.demo.springcloud.service.user;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.cloud.stream.annotation.EnableBinding;

import com.alisls.demo.springcloud.service.user.stream.MySink;

import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * 用户服务
 *
 * @author Ke Wang
 */
@SpringBootApplication
@EnableSwagger2
@EnableCircuitBreaker
@EnableFeignClients
@EnableBinding(MySink.class)
public class ServiceUserApp {

	public static void main(String[] args) {
		SpringApplication.run(ServiceUserApp.class, args);
	}
	
}
