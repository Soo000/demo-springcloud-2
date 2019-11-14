package com.alisls.demo.springcloud.provider.user;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.cloud.stream.annotation.EnableBinding;

import com.alisls.demo.springcloud.provider.user.stream.MySink;

@SpringBootApplication
@EnableCircuitBreaker
@EnableFeignClients
@EnableBinding(MySink.class)
public class ProviderUserApp {

    public static void main(String[] args) {
        SpringApplication.run(ProviderUserApp.class, args);
    }

}
