package com.alisls.demo.springcloud.service.user.api;

import com.alisls.demo.springcloud.service.user.dto.UserDTO;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 用户服务暴露接口
 *
 * @author Ke Wang
 */
@RequestMapping("/user")
public interface UserApi {

	@GetMapping("/getUserByUsername/{username}")
	UserDTO getUserByUsername(@PathVariable("username") String username);

    @GetMapping("/getUserWithPwdByUsername/{username}")
    UserDTO getUserWithPwdByUsername(@PathVariable("username") String username);
	
}
