package com.alisls.demo.springcloud.service.user.web;

import java.util.List;

import com.alisls.demo.springcloud.service.user.dto.UserDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.alisls.demo.springcloud.service.user.service.impl.InstanceService;
import org.springframework.web.client.RestTemplate;

@RestController
@RequestMapping("/instance")
public class InstanceController {

	@Autowired
	private InstanceService intanceService;

	@Autowired
	private RestTemplate restTemplate;
	
	/**
	 * 获取订单服务实例
	 */
	@GetMapping("/getOrderServiceInstance")
	public ResponseEntity<ServiceInstance> getOrderServiceInstance() {
		// 根据服务ID获取实例
		List<ServiceInstance> instances = intanceService.getInstances("demo-springcloud-2-provider-order");
		
		// 从实例中获取IP和端口
		ServiceInstance serviceInstance = instances.get(0);
		String host = serviceInstance.getHost();
		int port = serviceInstance.getPort();
		
		System.out.println("host = " + host);
		System.out.println("port = " + port);
		
		return ResponseEntity.ok(serviceInstance);
	}
	
	/**
	 * 获取服务实例
	 */
	@GetMapping("/getServiceInstance/{instanceId}")
	public ResponseEntity<ServiceInstance> getServiceInstance(@PathVariable String instanceId) {
		ServiceInstance serviceInstance = intanceService.getInstance(instanceId);
		return ResponseEntity.ok(serviceInstance);
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
	
}
