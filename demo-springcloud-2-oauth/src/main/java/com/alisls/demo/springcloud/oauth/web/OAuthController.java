package com.alisls.demo.springcloud.oauth.web;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping
@Slf4j
public class OAuthController {

	@Autowired
	private TokenStore tokenStore;
	
	@GetMapping("/userMe")
	private Principal principal(Principal principal) {
		log.debug("username {}", principal.getName());
		return principal;
	}
	
	/**
	 * 移除AccessToken和RefreshToken(相对于退出接口)
	 * @param principal 认证主体
	 * @param accessToken 访问令牌
	 */
	@DeleteMapping(value = "/removeToken", params = "accessToken")
	public void removeToken(Principal principal, String accessToken) {
		OAuth2AccessToken accessTokenTarget = tokenStore.readAccessToken(accessToken);
		if (accessTokenTarget != null) {
			// 移除AccessToken
			tokenStore.removeAccessToken(accessTokenTarget);
			// 移除RefreshToken
			tokenStore.removeRefreshToken(accessTokenTarget.getRefreshToken());
			
			// TODO 保存日志
		}
	}
	
}
