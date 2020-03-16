package com.alisls.demo.springcloud.zuul;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;

/**
 * Zuul网关启动类
 *
 * @author Ke Wang
 */
@SpringBootApplication
@EnableZuulProxy
public class ZuulOAuthApp {

	public static void main(String[] args) {
		SpringApplication.run(ZuulOAuthApp.class, args);
	}
	
}
