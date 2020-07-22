package com.alisls.demo.springcloud.gateway.config;

import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 路由配置类
 *
 * @author Ke Wang
 */
@Configuration
public class GatewayConfig {

    /**
     * 代码配置路由
     * 注意：通过代码配置路由，配置文件中配置的路由需要屏蔽掉
     */
    @Bean
    public RouteLocator routeLocator(RouteLocatorBuilder builder) {
        return builder.routes()
                .route("path_route", r -> r.path("/csdn").uri("https://blog.csdn.net"))
                .build();
    }

}
