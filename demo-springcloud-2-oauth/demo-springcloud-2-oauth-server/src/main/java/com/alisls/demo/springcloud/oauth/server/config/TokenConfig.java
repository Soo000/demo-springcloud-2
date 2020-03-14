package com.alisls.demo.springcloud.oauth.server.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.redis.RedisTokenStore;

/**
 * Token配置类
 *
 * @author Ke Wang
 */
@Configuration
public class TokenConfig {

    @Autowired
    private RedisConnectionFactory redisConnectionFactory;

    /**
     * 用Redis管理Token(Token存储在Redis里面)
     * @return
     */
    @Bean
    public TokenStore tokenStore() {
        return new RedisTokenStore(redisConnectionFactory);
    }

}
