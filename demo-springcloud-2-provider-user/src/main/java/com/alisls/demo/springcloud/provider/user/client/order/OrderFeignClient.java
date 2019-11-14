package com.alisls.demo.springcloud.provider.user.client.order;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.alisls.demo.springcloud.common.model.user.dto.UserDTO;

@FeignClient(value = "DEMO-SPRINGCLOUD-2-PROVIDER-ORDER", fallback = OrderFeignClientImpl.class)
public interface OrderFeignClient {

	@RequestMapping(value = "/demo-springcloud-2/provider-order/order/getOrder/{id}", method = RequestMethod.GET)
	UserDTO getOrder(@PathVariable("id") Long id);
	
}	
