package com.alisls.demo.springcloud.service.user.client.order;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.alisls.demo.springcloud.service.user.dto.UserDTO;

@FeignClient(value = "DEMO-SPRINGCLOUD-2-PROVIDER-ORDER", fallback = OrderFeignClientImpl.class)
public interface OrderFeignClient {

	@RequestMapping(value = "/order/getOrder/{id}", method = RequestMethod.GET)
	UserDTO getOrder(@PathVariable("id") Long id);
	
}	
