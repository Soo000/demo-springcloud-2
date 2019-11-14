package com.alisls.demo.springcloud.provider.user.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.alisls.demo.springcloud.common.response.DataResult;
import com.alisls.demo.springcloud.common.response.Response;
import com.alisls.demo.springcloud.provider.user.service.instance.InstanceService;

@RestController
@RequestMapping("/demo-springcloud-2/provider-user/instance")
public class InstanceController {

	@Autowired
	private InstanceService intanceService;
	
	@GetMapping("/getOrderServiceInstance")
	public Response getOrderServiceInstance() {
		// 根据服务ID获取实例
		List<ServiceInstance> instances = intanceService.getInstances("demo-springcloud-2-provider-order");
		
		// 从实例中获取IP和端口
		ServiceInstance serviceInstance = instances.get(0);
		String host = serviceInstance.getHost();
		int port = serviceInstance.getPort();
		
		System.out.println("host = " + host);
		System.out.println("port = " + port);
		
		return DataResult.ofSuccess(instances);
	}
	
}
