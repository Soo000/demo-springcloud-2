package com.alisls.demo.springcloud.service.user.web;

import com.alisls.demo.springcloud.service.user.service.impl.InstanceService;
import com.springcloud.common.model.dto.DataResult;
import com.springcloud.common.model.dto.Result;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.List;

/**
 * 实例管理
 *
 * @author Ke Wang
 */
@Api(tags = "实例管理")
@RestController
@RequestMapping("/instance")
@AllArgsConstructor
public class InstanceController {

	private final InstanceService intanceService;

	private final RestTemplate restTemplate;
	
	/**
	 * 获取服务实例
	 */
    @ApiOperation(value = "获取微服务实例", notes = "根据微服务实例的名字获取实例")
    @ApiImplicitParam(
            name = "instance",
            required = true,
            paramType = "path",
            dataType = "String",
            example = "demo-springcloud-2-provider-order",
            defaultValue = "demo-springcloud-2-provider-order"
    )
	@GetMapping("/getServiceInstance/{instance}")
	public Result getServiceInstance(@PathVariable String instance) {
        // 根据服务ID获取实例
        List<ServiceInstance> instances = intanceService.getInstances(instance);

        // 从实例中获取IP和端口
        ServiceInstance serviceInstance = instances.get(0);
        String host = serviceInstance.getHost();
        int port = serviceInstance.getPort();

        System.out.println("host = " + host);
        System.out.println("port = " + port);

		return DataResult.ofSuccess(serviceInstance);
	}

	/**
	 * RestTemplate方式调用微服务
	 */
    @ApiOperation(value = "RestTemplate方式调用微服务", notes = "RestTemplate方式调用微服务方法示例")
    @ApiImplicitParam(
            name = "orderId",
            required = true,
            paramType = "path",
            dataType = "Integer",
            example = "1234567890123456789"
    )
	@GetMapping("/getOrderByOrderId/{orderId}")
	public Result getUserByUsername(@PathVariable Long orderId) {
		String url = "http://DEMO-SPRINGCLOUD-2-SERVICE-ORDER/order/getOrderById/" + orderId;
		Object response = restTemplate.getForObject(url, Object.class);
		return DataResult.ofSuccess(response);
	}
	
}
