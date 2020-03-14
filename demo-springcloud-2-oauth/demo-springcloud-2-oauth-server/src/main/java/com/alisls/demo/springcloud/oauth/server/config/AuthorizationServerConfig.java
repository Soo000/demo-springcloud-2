package com.alisls.demo.springcloud.oauth.server.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.provider.token.TokenStore;

import javax.annotation.Resource;

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

    /**
     * 配置被允许访问此认证服务器的客户端信息
     * 1. 内存方式
     * 2. 数据库方式
     * @param clients
     * @throws Exception
     */
    @Override
    public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
        // 客户端信息配置在内存中
        clients.inMemory()
                // 客户端ID
                .withClient("www.alisls.com-pc")
                // 客户端密码，密码是需要加密的，通过PasswordEncoder加密
                .secret(passwordEncoder.encode("123456"))
                // 资源ID，如微服务名称。即当前客户端可以访问哪些微服务，如配置即可全部访问
                .resourceIds("demo-springcloud-service-user-service")
                /*
                 * 配置授权方式，这里可以配置多种模式
                 * authorization_code 授权码模式
                 * password 密码模式
                 * implicit 简化模式
                 * client_credentials 客户端模式
                 * refresh_token 刷新令牌
                 */
                .authorizedGrantTypes("authorization_code", "password", "implicit", "client_credentials", "refresh_token")
                /*
                 * 授权范围
                 * 指定访问微服务的方法, 传入的参数("all")只是一个标识，并不实际做任何操作
                 */
                .scopes("read")
                /*
                 * 是否自动授权
                 * false: 会跳转到授权页面，需要手动点击授权确认按钮
                 * true: 输入用户名密码后自动授权
                 */
                .autoApprove(false)
                // 客户端回调地址：授权码附在客户端指定的该“重定向URI”后面，之后带着授权码申请令牌
                .redirectUris("http://127.0.0.1:16001/codeReceiver")
                // 以下配置第二个客户端
                //.and()
                //.withClient("www.alisls.com-mobile")
                ;
    }

    /**
     *  关于认证服务器端点的配置
     * @param endpoints
     * @throws Exception
     */
    @Override
    public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
        // 密码模式需要向端点配置 authenticationManager
        endpoints.authenticationManager(authenticationManager);

        // 刷新令牌时使用
        endpoints.userDetailsService(customUserDetailsService);

        // 设置TokenStore
        endpoints.tokenStore(tokenStore);
    }

}
