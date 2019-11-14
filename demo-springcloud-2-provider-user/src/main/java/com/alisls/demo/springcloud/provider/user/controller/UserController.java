package com.alisls.demo.springcloud.provider.user.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.alisls.demo.springcloud.common.model.user.dto.UserDTO;
import com.alisls.demo.springcloud.common.response.DataResult;
import com.alisls.demo.springcloud.common.response.Response;
import com.alisls.demo.springcloud.common.response.Result;
import com.alisls.demo.springcloud.provider.user.client.order.OrderFeignClient;
import com.alisls.demo.springcloud.provider.user.service.instance.InstanceService;
import com.netflix.hystrix.contrib.javanica.annotation.DefaultProperties;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;

@RestController
@RequestMapping("/demo-springcloud-2/provider-user/user")
@DefaultProperties(defaultFallback = "defaultFallbackMethod")
public class UserController {

	@Autowired	
	private RestTemplate restTemplate;
	
	@Autowired
	private InstanceService instanceService;
	
	@Autowired
	private OrderFeignClient orderClient;
	
	@GetMapping("/getUserById/{id}")
	public Response getUserById(@PathVariable Long id) {
		UserDTO userDTO = new UserDTO();
		userDTO.setId(1L);
		userDTO.setUsername("测试用户");
		
		ServiceInstance orderInstance = instanceService.getInstances("demo-springcloud-2-provider-order").get(0);
		
		// 获取服务host地址（注意：如果是多网卡，会存在获取地址不是想要的地址问题）
		String host = orderInstance.getHost();
		int port = orderInstance.getPort();
		String orderId = "1";
		String url = "http://" + host + ":" + port + "/demo-springcloud-2/provider-order/order/getOrderById/" + orderId;
		Object response = restTemplate.getForObject(url, Object.class);
		System.out.println(response);
		
		return DataResult.ofSuccess(userDTO);
	}
	
	/**
	 * restTemplate 添加 ribbon负载均衡后的调用方式
	 */
	@GetMapping("/getUserByUsername/{username}")
	public Response getUserByUsername(@PathVariable String username) {
		UserDTO userDTO = new UserDTO();
		userDTO.setId(1L);
		userDTO.setUsername("测试用户");
		
		String orderId = "1";
		String url = "http://DEMO-SPRINGCLOUD-2-PROVIDER-ORDER/demo-springcloud-2/provider-order/order/getOrderById/" + orderId;
		Object response = restTemplate.getForObject(url, Object.class);
		System.out.println("response = " + response);
		
		return DataResult.ofSuccess(userDTO);
	}
	
	
	/**
	 * 获取用户以及用户订单信息
	 * 使用了 Hystrix 进行服务降级
	 */
	@GetMapping("/getUserWithOrder/{id}")
	@HystrixCommand(/* fallbackMethod = "getUserWithOrderFail" */
		commandProperties = {
			@HystrixProperty(name="execution.isolation.thread.timeoutInMilliseconds", value="1000")
		}
	)
	public Response getUserWithOrder(@PathVariable Long id) {
		UserDTO userDTO = new UserDTO();
		userDTO.setId(1L);
		userDTO.setUsername("测试用户");
		
		String orderId = "1";
		String url = "http://DEMO-SPRINGCLOUD-2-PROVIDER-ORDER/demo-springcloud-2/provider-order/order/getOrderById/" + orderId;
		Object response = restTemplate.getForObject(url, Object.class);
		System.out.println("response = " + response);
		return DataResult.ofSuccess(userDTO);
	}
	
	public Response getUserWithOrderFail(@PathVariable Long id) {
		System.out.println("getUserWithOrder 进行了降级");
		return DataResult.ofSuccess("服务器忙，请稍后再试！");
	}
	
	/**
	 * 使用 Feign 方式调用用户订单
	 */
	@GetMapping("/findUserWithOrder/{id}")
	public Response findUserWithOrder(@PathVariable Long id) {
		UserDTO userDTO = orderClient.getOrder(id);
		return DataResult.ofSuccess(userDTO);
	}
	
	/**
	 * 通用服务降级方法
	 */
	public Response defaultFallbackMethod() {
		return Result.ofFail();
	}
	
}
