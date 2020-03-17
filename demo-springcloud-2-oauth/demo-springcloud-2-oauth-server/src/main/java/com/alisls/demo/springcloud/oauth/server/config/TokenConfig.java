package com.alisls.demo.springcloud.oauth.server.config;

import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.druid.sql.visitor.functions.Char;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.JdbcTokenStore;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.store.JwtTokenStore;
import org.springframework.security.oauth2.provider.token.store.KeyStoreKeyFactory;
import org.springframework.security.oauth2.provider.token.store.redis.RedisTokenStore;

import javax.sql.DataSource;

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
        //jwtAccessTokenConverter.setSigningKey(SIGNING_KEY);

        /*
         * 采用非对称加密加密jwt
         *
         */
        // 密钥库资源
        ClassPathResource keyStoreResource = new ClassPathResource("oauth2.jks");
        // 密钥库口口令
        String storePass = "oauth2";
        // 使用密钥库和密钥库口令，用私钥进行签名
        KeyStoreKeyFactory keyStoreKeyFactory =
                new KeyStoreKeyFactory(keyStoreResource, storePass.toCharArray());

        // 密钥库别名
        String alias = "oauth2";
        jwtAccessTokenConverter.setKeyPair(keyStoreKeyFactory.getKeyPair(alias));
        return jwtAccessTokenConverter;
    }


    /**
     * 用Redis管理Token(Token存储在Redis里面)
     * @return
     */
    /*@Bean
    public TokenStore tokenStore() {
        return new RedisTokenStore(redisConnectionFactory);
    }*/

    /**
     * 配置Druid数据源
     * @return
     */
    @ConfigurationProperties(prefix = "spring.datasource")
    @Bean
    public DataSource dataSource() {
        return new DruidDataSource();
    }

    /**
     * 配置Jdbc的TokenStore
     * @return TokenStore
     */
    /*@Bean
    public TokenStore tokenStore() {
        return new JdbcTokenStore(dataSource());
    }*/

    /**
     * 使用JWT管理令牌
     * @return
     */
    @Bean
    public TokenStore tokenStore() {
        return new JwtTokenStore(jwtAccessTokenConverter());
    }

}
