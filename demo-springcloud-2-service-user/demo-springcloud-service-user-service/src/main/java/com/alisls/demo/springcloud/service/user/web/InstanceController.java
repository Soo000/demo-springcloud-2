package com.alisls.demo.springcloud.service.user.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.alisls.demo.springcloud.service.user.service.impl.InstanceService;

@RestController
@RequestMapping("/instance")
public class InstanceController {

	@Autowired
	private InstanceService intanceService;
	
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
	
}
