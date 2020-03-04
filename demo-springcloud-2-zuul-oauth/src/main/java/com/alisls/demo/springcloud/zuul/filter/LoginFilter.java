package com.alisls.demo.springcloud.zuul.filter;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.apache.http.HttpStatus;
import org.springframework.cloud.netflix.zuul.filters.support.FilterConstants;
import org.springframework.stereotype.Component;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;

/**
 * 登录校验过滤器
 */
@Component
public class LoginFilter extends ZuulFilter {

	@Override
	public boolean shouldFilter() {
		return true;
	}
	
	@Override
	public String filterType() {
		return FilterConstants.PRE_TYPE;
	}
	
	@Override
	public int filterOrder() {
		return FilterConstants.PRE_DECORATION_FILTER_ORDER - 1;
	}

	@Override
	public Object run() throws ZuulException {
		RequestContext ctx = RequestContext.getCurrentContext();
		HttpServletRequest request = ctx.getRequest();
		String accessToken = request.getParameter("token");
		if (StringUtils.isBlank(accessToken)) {
			// 不存在，未登录
			ctx.setSendZuulResponse(false);
			ctx.setResponseStatusCode(HttpStatus.SC_FORBIDDEN);
		}
		return null;
	}

}
