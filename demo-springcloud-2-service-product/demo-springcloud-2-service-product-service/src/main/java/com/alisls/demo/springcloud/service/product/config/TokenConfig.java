package com.alisls.demo.springcloud.service.product.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.store.JwtTokenStore;

/**
 * Token配置类
 *
 * @author Ke Wang
 */
@Configuration
public class TokenConfig {

    /**
     * JWT 对称密钥
     */
    public static final String SIGNING_KEY = "www.alisls.com";

    /**
     * 向容器注入JWT转换器JwtAccessTokenConverter
     * @return
     */
    @Bean
    public JwtAccessTokenConverter jwtAccessTokenConverter() {
        JwtAccessTokenConverter jwtAccessTokenConverter = new JwtAccessTokenConverter();
        // 采用对称加密签名令牌，对应资源服务器也要采用此密钥进行解密
        jwtAccessTokenConverter.setSigningKey(SIGNING_KEY);
        return jwtAccessTokenConverter;
    }

    /**
     * 使用JWT管理令牌
     * @return
     */
    @Bean
    public TokenStore tokenStore() {
        return new JwtTokenStore(jwtAccessTokenConverter());
    }

}
