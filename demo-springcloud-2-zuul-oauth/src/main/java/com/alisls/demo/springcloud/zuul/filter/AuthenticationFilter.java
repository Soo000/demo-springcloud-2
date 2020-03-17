package com.alisls.demo.springcloud.zuul.filter;

import com.alibaba.fastjson.JSON;
import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.stereotype.Component;
import org.springframework.util.Base64Utils;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * 认证过滤器
 * 在请求资源前，先通过此过滤器进行用户信息的解析和校验，转发
 *
 * @author Ke Wang
 */
@Component
@Slf4j
public class AuthenticationFilter extends ZuulFilter {

    /**
     * type: pre 表示在请求目标之前调用此过滤器
     *
     * @return
     */
    @Override
    public String filterType() {
        return "pre";
    }

    @Override
    public int filterOrder() {
        return 0;
    }

    @Override
    public boolean shouldFilter() {
        return true;
    }

    @Override
    public Object run() throws ZuulException {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        // 如果解析到令牌就会封装到OAuth2Authentication对象
        if (!(authentication instanceof OAuth2Authentication)) {
            return null;
        }

        log.info("网关获取到的认证对象{}", authentication);

        // 获取用户名
        Object principal = authentication.getPrincipal();
        // 获取用户对应的权限
        Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();
        // 权限的Set集合
        Set<String> authoritySet = AuthorityUtils.authorityListToSet(authorities);
        // 请求详情
        Object details = authentication.getDetails();

        // 将要传递的信息封装到map中
        Map<String, Object> result = new HashMap<>();
        result.put("principal", principal);
        result.put("authorities", authoritySet);
        result.put("details", details);

        // Zuul当前请求上下文
        RequestContext currentContext = RequestContext.getCurrentContext();
        String resultJson = JSON.toJSONString(result);
        String base64ResultJson = Base64Utils.encodeToString(resultJson.getBytes());

        // 将用户信息和权限转成JSON再通过base64编码，添加到请求头
        currentContext.addZuulRequestHeader("auth-token", base64ResultJson);

        return null;
    }

}
