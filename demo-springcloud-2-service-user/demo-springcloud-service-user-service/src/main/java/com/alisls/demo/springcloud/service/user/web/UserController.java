package com.alisls.demo.springcloud.service.user.web;

import com.alisls.demo.springcloud.service.order.dto.OrderDTO;
import com.alisls.demo.springcloud.service.user.client.order.OrderClient;
import com.alisls.demo.springcloud.service.user.dto.UserDTO;
import com.alisls.demo.springcloud.service.user.service.UserService;
import com.alisls.demo.springcloud.service.user.vo.UserOrderVO;
import com.netflix.hystrix.contrib.javanica.annotation.DefaultProperties;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import com.springcloud.common.model.dto.DataResult;
import com.springcloud.common.model.dto.Result;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
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
@DefaultProperties(defaultFallback = "defaultMethodFallback")
@AllArgsConstructor
@Log4j2
public class UserController {

    /**
     * 用户服务
     */
    private final UserService userService;

    /**
     * 订单服务Feign客户端
     */
    private final OrderClient orderClient;

    /**
     * 根据用户标识查询用户
     */
    @ApiOperation(value = "查询用户", notes = "根据用户标识查询用户")
    @ApiImplicitParam(
            name = "id",
            required = true,
            paramType = "path",
            dataType = "Long",
            example = "1234567890123456789"
    )
	@GetMapping("/getUserById/{id}")
    @HystrixCommand(fallbackMethod = "getUserByIdFallback")
	public Result getUserById(@PathVariable Long id) {
        try {
            Thread.sleep(4000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        UserDTO userDTO = userService.getUser(id);
		return DataResult.ofSuccess(userDTO);
	}

    /**
     * 根据用户标识查询用户，服务降级方法
     */
	public Result getUserByIdFallback(Long id) {
	    log.info("根据用户标识查询用户，服务调用超时");
        return DataResult.ofTimeout();
    }

    /**
     * 根据用户名查询用户
     */
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
	 * 根据用户标识查询用户和订单信息(使用了Hystrix方法级的服务降级配置commandProperties)
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
	        fallbackMethod = "getUserOrderFallback",
            commandProperties = {
                    @HystrixProperty(
                            name = "execution.isolation.thread.timeoutInMilliseconds",
                            value = "1000")
            }
	)
	public ResponseEntity<UserOrderVO> getUserOrder(@PathVariable Long userId) {
	    // 查询用户信息
		UserDTO userDTO = userService.getUser(userId);
        // 查询订单信息
        OrderDTO orderDTO = orderClient.getOrderById(1L);

        // 返回数据
        UserOrderVO userOrderVO = new UserOrderVO(userDTO, orderDTO);
        return ResponseEntity.ok(userOrderVO);
	}

    /**
     * 查询用户订单降级方法
     */
	public ResponseEntity<UserOrderVO> getUserOrderFallback(@PathVariable Long id) {
		log.info("根据用户标识查询用户和订单信息接口调用超时");
		return ResponseEntity.ok(new UserOrderVO());
	}
	
	/**
	 * 通用服务降级方法
	 */
	public ResponseEntity<UserDTO> defaultMethodFallback() {
		return ResponseEntity.ok(new UserDTO());
	}
	
}
