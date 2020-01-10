package com.alisls.demo.springcloud.gateway.config;

import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class GatewayConfig {

	/**
	 * 自定义路由
	 */
	@Bean
    public RouteLocator customRouteLocator(RouteLocatorBuilder builder) {
		
		// 访问 http://www.xxx.com/user 会自动跳转到 http://localhost:15001/user TODO 暂未测试通过
        return builder.routes()
                .route("my-user", r -> r.path("/user")
                        .uri("http://localhost:15001"))
                .build();
    }
	
}
