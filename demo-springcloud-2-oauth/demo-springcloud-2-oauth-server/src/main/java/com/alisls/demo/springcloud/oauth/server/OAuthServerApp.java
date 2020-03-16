package com.alisls.demo.springcloud.oauth.server;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * 认证服务器启动类
 */
@SpringBootApplication
public class OAuthServerApp {

    public static void main(String[] args) {
        SpringApplication.run(OAuthServerApp.class, args);
    }

}
