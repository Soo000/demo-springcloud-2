package com.alisls.demo.springcloud.service.user.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.cloud.netflix.ribbon.RibbonLoadBalancerClient;
import org.springframework.stereotype.Service;

@Service
public class InstanceService {

	@Autowired
	private DiscoveryClient discoveryClient;
	
	@Resource
	private RibbonLoadBalancerClient loadBalancerClient;
	
	public List<ServiceInstance> getInstances(String instanceId) {
		// 根据服务ID获取实例
		return discoveryClient.getInstances(instanceId);
	}
	
	/**
	 * 通过 ribbon 负载均衡得到一个服务实例
	 */
	public ServiceInstance getInstance(String instanceId) {
		return loadBalancerClient.choose(instanceId);
	}
	
}
