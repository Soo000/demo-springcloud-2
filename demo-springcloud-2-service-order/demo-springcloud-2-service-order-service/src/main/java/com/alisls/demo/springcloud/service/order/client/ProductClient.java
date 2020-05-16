package com.alisls.demo.springcloud.service.order.client;

import com.alisls.demo.springcloud.service.order.interceptor.FeignOAuth2RequestInterceptor;
import com.alisls.demo.springcloud.service.product.api.IProductService;
import org.springframework.cloud.openfeign.FeignClient;
import springfox.documentation.annotations.ApiIgnore;

/**
 * 商品服务客户端
 *
 * @author Ke Wang
 */
@ApiIgnore
@FeignClient(name = "demo-springcloud-2-service-product",
        configuration = FeignOAuth2RequestInterceptor.class)
public interface ProductClient extends IProductService {
}
