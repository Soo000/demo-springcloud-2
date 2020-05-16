package com.alisls.demo.springcloud.service.order;

import com.alisls.demo.springcloud.service.order.stream.MySource;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.cloud.stream.annotation.EnableBinding;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * 订单服务
 *
 * @author Ke Wang
 */
@SpringBootApplication
@EnableSwagger2
@EnableFeignClients
@EnableBinding(MySource.class)
public class ServiceOrderApp {

	public static void main(String[] args) {
		SpringApplication.run(ServiceOrderApp.class, args);
	}
	
}
