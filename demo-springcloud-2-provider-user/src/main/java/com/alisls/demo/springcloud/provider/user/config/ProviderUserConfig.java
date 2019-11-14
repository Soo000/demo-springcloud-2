package com.alisls.demo.springcloud.provider.user.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class ProviderUserConfig {

	@Bean
	public RestTemplate restTemplate() {
		return new RestTemplate();
	}
	
}