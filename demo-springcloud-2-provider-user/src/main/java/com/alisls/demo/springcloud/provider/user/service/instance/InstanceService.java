package com.alisls.demo.springcloud.provider.user.service.instance;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.stereotype.Service;

@Service
public class InstanceService {

	@Autowired
	private DiscoveryClient discoveryClient;
	
	public List<ServiceInstance> getInstances(String instanceId) {
		// 根据服务ID获取实例
		return discoveryClient.getInstances(instanceId);
	}
	
}
