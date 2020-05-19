package com.alisls.demo.springcloud.service.user.client.order;

import com.alisls.demo.springcloud.service.order.api.IOrderService;
import org.springframework.cloud.openfeign.FeignClient;
import springfox.documentation.annotations.ApiIgnore;

/**
 * 订单服务客户端
 *
 * fallback：定义容错的处理类，当调用远程接口失败或超时时，会调用对应接口的容错逻辑，
 * fallback指定的类必须实现@FeignClient标记的接口。
 *
 * @author Ke Wang
 */
@ApiIgnore
@FeignClient(
        value = "DEMO-SPRINGCLOUD-2-SERVICE-ORDER"
)
public interface OrderClient extends IOrderService {
}
