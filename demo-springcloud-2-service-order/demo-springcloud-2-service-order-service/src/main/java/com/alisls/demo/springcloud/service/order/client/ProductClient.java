package com.alisls.demo.springcloud.service.order.client;

import com.alisls.demo.springcloud.service.order.interceptor.FeignOAuth2RequestInterceptor;
import com.alisls.demo.springcloud.service.product.api.IProduct;
import org.springframework.cloud.openfeign.FeignClient;

/**
 * 商品服务客户端
 */
@FeignClient(name = "demo-springcloud-2-service-product",
        configuration = FeignOAuth2RequestInterceptor.class)
public interface ProductClient extends IProduct {
}
