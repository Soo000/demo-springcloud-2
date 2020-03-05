package com.alisls.demo.springcloud.oauth.client;

import org.springframework.cloud.openfeign.FeignClient;

import com.alisls.demo.springcloud.service.user.api.UserApi;

@FeignClient(name = "demo-springcloud-2-user-service")
public interface UserClient extends UserApi {

}
