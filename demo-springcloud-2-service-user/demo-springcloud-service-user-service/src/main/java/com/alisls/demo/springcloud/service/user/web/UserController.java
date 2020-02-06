package com.alisls.demo.springcloud.service.user.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.alisls.demo.springcloud.service.user.dto.UserDTO;
import com.alisls.demo.springcloud.service.user.service.impl.InstanceService;
import com.netflix.hystrix.contrib.javanica.annotation.DefaultProperties;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;

@RestController
@RequestMapping("/user")
@DefaultProperties(defaultFallback = "defaultFallbackMethod")
public class UserController {

	@Autowired	
	private RestTemplate restTemplate;
	
	@Autowired
	private InstanceService instanceService;
	
	//@Autowired
	//private OrderFeignClient orderClient;
	
	@GetMapping("/getUserById/{id}")
	public ResponseEntity<UserDTO> getUserById(@PathVariable Long id) {
		UserDTO userDTO = new UserDTO();
		userDTO.setId(1L);
		userDTO.setUsername("测试用户");
		
		ServiceInstance orderInstance = instanceService.getInstances("demo-springcloud-2-provider-order").get(0);
		
		// 获取服务host地址（注意：如果是多网卡，会存在获取地址不是想要的地址问题）
		String host = orderInstance.getHost();
		int port = orderInstance.getPort();
		String orderId = "1";
		String url = "http://" + host + ":" + port + "/order/getOrderById/" + orderId;
		Object response = restTemplate.getForObject(url, Object.class);
		System.out.println(response);
		
		return ResponseEntity.ok(userDTO);
	}
	
	@GetMapping("/getTestUserById/{id}")
	public ResponseEntity<UserDTO> getTestUserById(@PathVariable Long id) {
		UserDTO userDTO = new UserDTO();
		userDTO.setId(id);
		userDTO.setUsername("测试用户");
		return ResponseEntity.ok(userDTO);
	}
	
	/**
	 * restTemplate 添加 ribbon负载均衡后的调用方式
	 */
	@GetMapping("/getUserByUsername/{username}")
	public ResponseEntity<UserDTO> getUserByUsername(@PathVariable String username) {
		UserDTO userDTO = new UserDTO();
		userDTO.setId(1L);
		userDTO.setUsername("测试用户");
		
		String orderId = "1";
		String url = "http://DEMO-SPRINGCLOUD-2-PROVIDER-ORDER/order/getOrderById/" + orderId;
		Object response = restTemplate.getForObject(url, Object.class);
		System.out.println("response = " + response);
		
		return ResponseEntity.ok(userDTO);
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
	public ResponseEntity<UserDTO> getUserWithOrder(@PathVariable Long id) {
		UserDTO userDTO = new UserDTO();
		userDTO.setId(1L);
		userDTO.setUsername("测试用户");
		
		String orderId = "1";
		String url = "http://DEMO-SPRINGCLOUD-2-PROVIDER-ORDER/order/getOrderById/" + orderId;
		Object response = restTemplate.getForObject(url, Object.class);
		System.out.println("response = " + response);
		return ResponseEntity.ok(userDTO);
	}
	
	public ResponseEntity<UserDTO> getUserWithOrderFail(@PathVariable Long id) {
		System.out.println("getUserWithOrder 进行了降级");
		return ResponseEntity.ok(new UserDTO());
	}
	
	/**
	 * 使用 Feign 方式调用用户订单
	 */
	@GetMapping("/findUserWithOrder/{id}")
	public ResponseEntity<UserDTO> findUserWithOrder(@PathVariable Long id) {
		//UserDTO userDTO = orderClient.getOrder(id);
		return ResponseEntity.ok(null);
	}
	
	/**
	 * 通用服务降级方法
	 */
	public ResponseEntity<UserDTO> defaultFallbackMethod() {
		return ResponseEntity.ok(new UserDTO());
	}
	
}
