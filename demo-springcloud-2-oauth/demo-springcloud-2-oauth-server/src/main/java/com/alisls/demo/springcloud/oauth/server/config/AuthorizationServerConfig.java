package com.alisls.demo.springcloud.oauth.server.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.ClientDetailsService;
import org.springframework.security.oauth2.provider.client.JdbcClientDetailsService;
import org.springframework.security.oauth2.provider.code.AuthorizationCodeServices;
import org.springframework.security.oauth2.provider.code.JdbcAuthorizationCodeServices;
import org.springframework.security.oauth2.provider.token.TokenStore;

import javax.annotation.Resource;
import javax.sql.DataSource;

/**
 * 认证服务器配置类
 * @EnableAuthorizationServer 注解开启了认证服务器功能
 */
@Configuration
@EnableAuthorizationServer
public class AuthorizationServerConfig extends AuthorizationServerConfigurerAdapter {

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Resource
    private UserDetailsService customUserDetailsService;

    @Autowired
    private TokenStore tokenStore;

    @Autowired
    private DataSource dataSource;

    /**
     * 授权码管理策略
     * 向容器注入JdbcAuthorizationCodeServices，用来用Jdbc管理授权码
     * @return
     */
    @Bean
    public AuthorizationCodeServices jdbcAuthorizationCodeServices() {
        return new JdbcAuthorizationCodeServices(dataSource);
    }

    /**
     * 客户端管理策略
     * 向容器注入JdbcClientDetailsService，用来用Jdbc管理授客户端
     * @return
     */
    @Bean
    public ClientDetailsService jdbcClientDetailsService() {
        return new JdbcClientDetailsService(dataSource);
    }

    /**
     * 内存方式配置被允许访问此认证服务器的客户端信息
     * 1. 内存方式
     * 2. 数据库方式
     * @param clients
     * @throws Exception
     */
    /*@Override
    public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
        // 客户端信息配置在内存中
        clients.inMemory()
                // 客户端ID
                .withClient("www.alisls.com-pc")
                // 客户端密码，密码是需要加密的，通过PasswordEncoder加密
                .secret(passwordEncoder.encode("123456"))
                // 资源ID，如微服务名称。即当前客户端可以访问哪些微服务，如配置即可全部访问
                .resourceIds("demo-springcloud-service-product-service")
                *//*
                 * 配置授权方式，这里可以配置多种模式
                 * authorization_code 授权码模式
                 * password 密码模式
                 * implicit 简化模式
                 * client_credentials 客户端模式
                 * refresh_token 刷新令牌
                 *//*
                .authorizedGrantTypes("authorization_code", "password", "implicit", "client_credentials", "refresh_token")
                *//*
                 * 授权范围
                 * 指定访问微服务的方法, 传入的参数("all")只是一个标识，并不实际做任何操作
                 *//*
                .scopes("read")
                *//*
                 * 是否自动授权
                 * false: 会跳转到授权页面，需要手动点击授权确认按钮
                 * true: 输入用户名密码后自动授权
                 *//*
                .autoApprove(false)
                // 客户端回调地址：授权码附在客户端指定的该“重定向URI”后面，之后带着授权码申请令牌
                .redirectUris("http://127.0.0.1:16001/codeReceiver")
                // 设置token的有效时长，默认12小时
                .accessTokenValiditySeconds(60 * 60 * 24)
                // 设置刷新token的有效时长，默认30天
                .refreshTokenValiditySeconds(60 * 60 * 24 * 60)
                // 以下为模拟SSO Client 1
                .and()
                .withClient("client1")
                .secret(passwordEncoder.encode("client1-secret"))
                .authorizedGrantTypes("authorization_code")
                // 以下为模拟SSO Client 2
                .and()
                .withClient("client2")
                .secret(passwordEncoder.encode("client2-secret"))
                .authorizedGrantTypes("authorization_code");
    }*/

    /**
     * 数据方式配置被允许访问此认证服务器的客户端信息
     * 1. 内存方式
     * 2. 数据库方式
     * @param clients
     * @throws Exception
     */
    @Override
    public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
        // 设置用jdbc管理客户端
        clients.withClientDetails(jdbcClientDetailsService());
    }

    /**
     * 关于认证服务器端点的配置
     * @param endpoints
     * @throws Exception
     */
    @Override
    public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
        // 设置授权码管理策略为jdbc, 授权码会保存在表oauth_code中（如果授权码被使用，对应的数据就会被删除）
        endpoints.authorizationCodeServices(jdbcAuthorizationCodeServices());

        // 密码模式需要向端点配置 authenticationManager
        endpoints.authenticationManager(authenticationManager);

        // 刷新令牌时使用
        endpoints.userDetailsService(customUserDetailsService);

        // 设置TokenStore
        endpoints.tokenStore(tokenStore);
    }

    /**
     * 令牌端点的安全配置
     * @param security
     * @throws Exception
     */
    @Override
    public void configure(AuthorizationServerSecurityConfigurer security) throws Exception {
        // 设置JWT公钥端点不需要认证可用（默认拒绝访问）
        security.tokenKeyAccess("permitAll()");
        // 设置检查令牌端点认证后可用
        security.checkTokenAccess("isAuthenticated()");
    }

}
