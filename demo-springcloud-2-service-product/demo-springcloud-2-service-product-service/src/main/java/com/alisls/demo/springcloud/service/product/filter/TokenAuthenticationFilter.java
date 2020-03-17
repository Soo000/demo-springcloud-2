package com.alisls.demo.springcloud.service.product.filter;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.ArrayUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.util.Base64Utils;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * 自定义TokenAuthenticationFilter过滤器，继承OncePerRequestFilter
 */
@Component
@Slf4j
public class TokenAuthenticationFilter extends OncePerRequestFilter {

    /**
     * 获取网关转发过来的请求头中保存的Token值（明文）
     * @param request
     * @param response
     * @param filterChain
     * @throws ServletException
     * @throws IOException
     */
    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain) throws ServletException, IOException {
        // 从header中获取authToken
        String authToken = request.getHeader("auth-token");
        if (!StringUtils.isEmpty(authToken)) {
            log.info("商品资源服务器从请求头中获取Key为auth-token的值为{}", authToken);
            // 1.解析Token
            byte[] bytes = Base64Utils.decodeFromString(authToken);
            String authTokenJson = new String(bytes);
            // 2.转出Json对象
            JSONObject jsonObject = JSON.parseObject(authTokenJson);
            // 用户名
            Object principal = jsonObject.get("principal");
            // 请求详情
            Object details = jsonObject.get("details");
            // 用户权限 sys:user:add,sys:user:query
            String authorities = ArrayUtils.toString(jsonObject.getJSONArray("authorities").toArray());
            List<GrantedAuthority> grantedAuthorities = AuthorityUtils.commaSeparatedStringToAuthorityList(authorities);

            // 自己构建UsernamePasswordAuthenticationToken对象，SpringSecurity就会自己进行权限判断
            UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken
                    = new UsernamePasswordAuthenticationToken(principal, null, grantedAuthorities);
            // 设置详细信息
            usernamePasswordAuthenticationToken.setDetails(details);

            // 将构建的 Authentication(usernamePasswordAuthenticationToken) 设置到上下文中
            SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
        }

        // 放行过滤器
        filterChain.doFilter(request, response);
    }

}
