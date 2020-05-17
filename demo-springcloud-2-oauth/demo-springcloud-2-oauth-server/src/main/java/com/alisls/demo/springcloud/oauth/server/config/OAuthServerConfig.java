package com.alisls.demo.springcloud.oauth.server.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * 微服务配置类
 *
 * @author Ke Wang
 */
@Configuration
public class OAuthServerConfig {

    /**
     * BCryptPasswordEncoder 加密算法
     *
     * @return PasswordEncoder
     */
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    /**
     * 国密SM2加密算法
     *
     * @return PasswordEncoder
     */
    /*@Bean
    public PasswordEncoder passwordEncoder() {
        return new Sm2PasswordEncoder();
    }*/

}
