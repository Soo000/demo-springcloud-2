package com.alisls.demo.springcloud.service.order;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.stream.annotation.EnableBinding;

import com.alisls.demo.springcloud.service.order.stream.MySource;

@SpringBootApplication
@EnableBinding(MySource.class)
public class ServiceOrderApp {

	public static void main(String[] args) {
		SpringApplication.run(ServiceOrderApp.class, args);
	}
	
}
