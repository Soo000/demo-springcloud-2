package com.alisls.demo.springcloud.oauth.sso.client1.config;

import org.springframework.boot.autoconfigure.security.oauth2.client.EnableOAuth2Sso;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

/**
 * 单点登录配置类
 * 注解 @EnableOAuth2Sso 开启单点登录功能
 * @author Ke Wang
 */
@Configuration
@EnableOAuth2Sso
public class SsoSecurityConfig extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                // 首页所有人都可以访问
                .antMatchers("/").permitAll()
                // 其它页面要认真后才可访问
                .anyRequest().authenticated();
    }

}
