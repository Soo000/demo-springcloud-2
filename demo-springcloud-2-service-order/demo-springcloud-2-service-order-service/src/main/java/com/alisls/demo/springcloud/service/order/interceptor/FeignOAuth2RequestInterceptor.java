package com.alisls.demo.springcloud.service.order.interceptor;

import feign.RequestInterceptor;
import feign.RequestTemplate;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpHeaders;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;

/**
 * Feign OAuth 请求拦截器
 */
@Configuration
@Slf4j
public class FeignOAuth2RequestInterceptor implements RequestInterceptor {

    @Override
    public void apply(RequestTemplate requestTemplate) {
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        String accessToken = request == null ? StringUtils.EMPTY : request.getHeader(HttpHeaders.AUTHORIZATION);
        log.info("拦截Feign的Http请求发送，从当前Request的Header中获取AUTHORIZATION值为{}", accessToken);
        requestTemplate.header(HttpHeaders.AUTHORIZATION, accessToken);
    }

}
