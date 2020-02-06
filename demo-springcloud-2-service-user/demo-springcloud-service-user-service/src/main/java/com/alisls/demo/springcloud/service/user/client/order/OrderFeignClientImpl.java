package com.alisls.demo.springcloud.service.user.client.order;

import org.springframework.stereotype.Component;

import com.alisls.demo.springcloud.service.user.dto.UserDTO;

@Component
public class OrderFeignClientImpl implements OrderFeignClient {

	@Override
	public UserDTO getOrder(Long id) {
		UserDTO userDTO = new UserDTO();
		userDTO.setId(-1L);
		userDTO.setUsername("发生了熔断");
		return userDTO;
	}

}
