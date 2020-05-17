package com.alisls.demo.springcloud.service.product.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.token.RemoteTokenServices;
import org.springframework.security.oauth2.provider.token.ResourceServerTokenServices;
import org.springframework.security.oauth2.provider.token.TokenStore;

/**
 * 资源服务器配置类
 * 注解 @EnableResourceServer 开启资源服务器（获取资源服务器中的资源，必须带有token才能访问）
 * 注解 @EnableGlobalMethodSecurity(prePostEnabled = true) 开启方法级别权限控制
 *
 * @author Ke Wang
 */
@Configuration
@EnableResourceServer
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class ResourceServerConfig extends ResourceServerConfigurerAdapter {

    private static final String RESOURCE_ID = "demo-springcloud-service-all";

    @Autowired
    private TokenStore tokenStore;

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
     * 建一个远程TokenService（即连接到认证服务器的进行token校验的服务）
     * @return ResourceServerTokenServices
     */
    /*public ResourceServerTokenServices tokenService() {
        // 创建一个远程TokenService（即连接到认证服务器的进行token校验的服务），需求设置该端点认证后可访问
        RemoteTokenServices tokenService = new RemoteTokenServices();
        tokenService.setCheckTokenEndpointUrl("http://localhost:16001/auth/oauth/check_token");
        tokenService.setClientId("www.alisls.com-pc");
        tokenService.setClientSecret("123456");
        return tokenService;
    }*/

    /**
     * 控制令牌范围权限和授权规则
     * @param http
     * @throws Exception
     */
    @Override
    public void configure(HttpSecurity http) throws Exception {
        // 设定Session策略为不创建HttpSession实例
        http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);

        // 设置资源访问授权规则
        http.authorizeRequests()
                // 设置访问资源/product/listProducts需要权限product:listProducts（注意顺序，不能放到后面来写）
                .antMatchers("/product/listProducts").hasAuthority("product:listProducts")
                // 设定资源服务器要求所有请求都必须有all范围(在认证服务器上针对客户端配置的范围)
                .antMatchers("/**").access("#oauth2.hasScope('PRODUCT_API')");
    }

}
