package com.alisls.demo.springcloud.zuul.filter;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.exception.ZuulException;

public class ParamFilter extends ZuulFilter {

	@Override
	public boolean shouldFilter() {
		return false;
	}

	@Override
	public Object run() throws ZuulException {
		/*RequestContext ctx = RequestContext.getCurrentContext();
		HttpServletRequest request = ctx.getRequest();
		Object accessToken = request.getParameter("token");
		if (accessToken != null) {
			return null;
		}
		
		ctx.setSendZuulResponse(false);
		ctx.setResponseStatusCode(401);
		try {
			ctx.getResponse().getWriter().write("必须传入 token 参数！");
		} catch (IOException e) {
			e.printStackTrace();
		}*/
		return null;

	}

	@Override
	public String filterType() {
		return null;
	}

	@Override
	public int filterOrder() {
		return 0;
	}

}
