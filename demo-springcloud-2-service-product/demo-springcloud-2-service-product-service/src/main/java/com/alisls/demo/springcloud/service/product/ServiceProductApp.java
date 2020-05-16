package com.alisls.demo.springcloud.service.product;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * 商品服务
 *
 * @author Ke Wang
 */
@SpringBootApplication
@EnableSwagger2
public class ServiceProductApp {

    public static void main(String[] args) {
        SpringApplication.run(ServiceProductApp.class, args);
    }

}
