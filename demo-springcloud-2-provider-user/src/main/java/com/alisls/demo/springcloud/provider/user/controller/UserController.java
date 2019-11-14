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
import com.alisls.demo.springcloud.provider.user.service.instance.InstanceService;

@RestController
@RequestMapping("/demo-springcloud-2/provider-user/user")
public class UserController {

	@Autowired	
	private RestTemplate restTemplate;
	
	@Autowired
	private InstanceService instanceService;
	
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
	
}
