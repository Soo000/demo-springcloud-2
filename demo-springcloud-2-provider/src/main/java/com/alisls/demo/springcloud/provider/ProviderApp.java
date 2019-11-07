package com.alisls.demo.springcloud.provider;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.stream.annotation.EnableBinding;

import com.alisls.demo.springcloud.provider.stream.MySource;

@SpringBootApplication
@EnableBinding(MySource.class)
public class ProviderApp {

	public static void main(String[] args) {
		SpringApplication.run(ProviderApp.class, args);
	}
	
}
