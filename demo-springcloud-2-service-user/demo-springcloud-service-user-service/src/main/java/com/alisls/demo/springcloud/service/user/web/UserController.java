package com.alisls.demo.springcloud.service.user.web;

import com.alisls.demo.springcloud.service.user.client.order.OrderFeignClient;
import com.alisls.demo.springcloud.service.user.dto.UserDTO;
import com.alisls.demo.springcloud.service.user.service.UserService;
import com.netflix.hystrix.contrib.javanica.annotation.DefaultProperties;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 用户管理
 *
 * @author Ke Wang
 */
@Api(description = "用户管理")
@RestController
@RequestMapping("/user")
@DefaultProperties(defaultFallback = "defaultFallbackMethod")
@AllArgsConstructor
public class UserController {

    /**
     * 用户服务
     */
    private final UserService userService;

    /**
     * 订单服务Feign客户端
     */
    private final OrderFeignClient orderFeignClient;

    @ApiOperation(value = "查询用户", notes = "根据用户标识查询用户")
    @ApiImplicitParam(
            name = "id",
            required = true,
            paramType = "path",
            dataType = "Long",
            example = "1234567890123456789"
    )
	@GetMapping("/getUserById/{id}")
	public ResponseEntity<UserDTO> getUserById(@PathVariable Long id) {
		UserDTO userDTO = userService.getUser(id);
		return ResponseEntity.ok(userDTO);
	}

    @ApiOperation(value = "查询用户", notes = "根据用户名查询用户")
    @ApiImplicitParam(
            name = "username",
            required = true,
            paramType = "path",
            dataType = "String",
            example = "wangke"
    )
	@GetMapping("/getUserByUsername/{username}")
	public ResponseEntity<UserDTO> getUserByName(@PathVariable String username) {
        UserDTO userDTO = userService.getUser(username);
		return ResponseEntity.ok(userDTO);
	}

	/**
	 * 根据用户标识查询用户和订单信息(使用了 Hystrix 进行服务降级)
	 */
	@ApiOperation(value = "查询用户订单", notes = "根据用户标识查询用户和订单信息")
    @ApiImplicitParam(
            name = "userId",
            required = true,
            paramType = "path",
            dataType = "Long",
            example = "1234567890123456789"
    )
	@GetMapping("/getUserOrder/{userId}")
	@HystrixCommand(
	        fallbackMethod = "getUserOrderFail",
            commandProperties = {
                @HystrixProperty(
                        name="execution.isolation.thread.timeoutInMilliseconds",
                        value="1000")
            }
	)
	public ResponseEntity<UserDTO> getUserOrder(@PathVariable Long userId) {
	    // 查询用户信息
		UserDTO userDTO = userService.getUser(userId);
        // 查询订单信息
        UserDTO orderDTO = orderFeignClient.getOrder(1L);
        // 返回数据
        return ResponseEntity.ok(userDTO);
	}

    /**
     * 查询用户订单服务降级方法
     * @param id
     * @return
     */
	public ResponseEntity<UserDTO> getUserOrderFail(@PathVariable Long id) {
		System.out.println("getUserWithOrder 进行了降级");
		return ResponseEntity.ok(new UserDTO());
	}
	
	/**
	 * 通用服务降级方法
	 */
	public ResponseEntity<UserDTO> defaultFallbackMethod() {
		return ResponseEntity.ok(new UserDTO());
	}
	
}
