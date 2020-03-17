package com.alisls.demo.springcloud.zuul.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.token.TokenStore;

/**
 * 网关资源服务器用于管理所有的资源（认证服务器资源、商品服务器资源）
 *
 * @author Ke Wang
 */
@Configuration
public class ResourceServerConfig {

    private static final String RESOURCE_ID = "demo-springcloud-service-all";

    @Autowired
    private TokenStore tokenStore;

    /**
     * 认证资源服务器配置类
     * 注解 @EnableResourceServer 开启资源服务器（获取资源服务器中的资源，必须带有token才能访问）
     */
    @Configuration
    @EnableResourceServer
    public class AuthResourceServerConfig extends ResourceServerConfigurerAdapter {

        @Override
        public void configure(ResourceServerSecurityConfigurer resources) throws Exception {
            /*
             * 设置当前资源服务器的ID；
             * 认证服务会认证客户端有没有访问这个资源ID的权限
             */
            resources.resourceId(RESOURCE_ID)
//            .tokenServices(tokenService());
                    // 设置tokenStore, 使用JWT token进行校验
                    .tokenStore(tokenStore);
        }

        /**
         * 控制令牌范围权限和授权规则
         * @param http
         * @throws Exception
         */
        @Override
        public void configure(HttpSecurity http) throws Exception {
            // 设置资源访问授权规则(认证服务器的都放行)
            http.authorizeRequests()
                    .anyRequest().permitAll();
        }

    }

    /**
     * 商品资源服务器配置类
     * 注解 @EnableResourceServer 开启资源服务器（获取资源服务器中的资源，必须带有token才能访问）
     */
    @Configuration
    @EnableResourceServer
    public class ProductResourceServerConfig extends ResourceServerConfigurerAdapter {

        @Override
        public void configure(ResourceServerSecurityConfigurer resources) throws Exception {
            /*
             * 设置当前资源服务器的ID；
             * 认证服务会认证客户端有没有访问这个资源ID的权限
             */
            resources.resourceId(RESOURCE_ID)
//            .tokenServices(tokenService());
                    // 设置tokenStore, 使用JWT token进行校验
                    .tokenStore(tokenStore);
        }

        /**
         * 控制令牌范围权限和授权规则
         * @param http
         * @throws Exception
         */
        @Override
        public void configure(HttpSecurity http) throws Exception {
            // 设置资源访问授权规则
            http.authorizeRequests()
                    // 设定所有请求商品资源服务器的都必须有PRODUCT_API范围(在认证服务器上针对客户端配置的范围)
                    .antMatchers("/product/**")
                    .access("#oauth2.hasScope('PRODUCT_API')");
        }

    }

}
