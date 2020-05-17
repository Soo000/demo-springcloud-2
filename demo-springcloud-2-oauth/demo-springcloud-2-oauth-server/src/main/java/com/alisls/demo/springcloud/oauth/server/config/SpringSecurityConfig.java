package com.alisls.demo.springcloud.oauth.server.config;

import com.alisls.demo.springcloud.oauth.server.service.CustomUserDetailsServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.annotation.Resource;

/**
 * SpringSecurity 安全配置类
 * @EnableWebSecurity 开启安全配置
 *
 * @author Ke Wang
 */
@Configuration
@EnableWebSecurity
public class SpringSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Resource
    private UserDetailsService customUserDetailsService;

    /**
     * 用户信息（保持在内存中）
     * @param auth 认证管理
     * @throws Exception 用户认证异常信息
     */
    /*@Autowired
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.inMemoryAuthentication()
			    .withUser("admin")
			    .password(passwordEncoder.encode("admin"))
                *//*
                 * 配置角色
                 *//*
                .roles("USER", "ADMIN")
                *//*
                 * 配置权限
                 *//*
                .authorities("product");
    }*/

    /**
     * 用户信息（从UserDetailsService接口中查询）
     * @param auth
     * @throws Exception
     */
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        // 设置UserDetailsService
        auth.userDetailsService(customUserDetailsService);
    }

    /**
     * 如果是密码模式，需要向容器添加 AuthenticationManager
     * @return
     * @throws Exception
     */
    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

}
