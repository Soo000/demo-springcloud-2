package com.alisls.demo.springcloud.provider.order;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.cloud.stream.annotation.EnableBinding;

import com.alisls.demo.springcloud.provider.order.stream.MySource;

@SpringBootApplication
@EntityScan(basePackages = {"com.alisls.demo.springcloud.provider.order.entity"})
@EnableBinding(MySource.class)
public class ProviderOrderApp {

	public static void main(String[] args) {
		SpringApplication.run(ProviderOrderApp.class, args);
	}
	
}