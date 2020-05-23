package com.alisls.demo.springcloud.service.user.config;

import com.demo.springcloud.common.core.util.IdWorker;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

/**
 * 用户服务配置类
 *
 * @author Ke Wang
 */
@Configuration
public class UserConfig {

	@Bean
	public IdWorker idWorker() {
		return new IdWorker();
	}

	@Bean
	@LoadBalanced
	public RestTemplate restTemplate() {
		return new RestTemplate();
	}
	
}
