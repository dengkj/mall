package com.dengkj.mall.product;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * @author dengkj
 * @time 2022-02-18 17:18:37
 * @description
 */
@SpringBootApplication
@EnableDiscoveryClient
public class ProductApplication {

    public static void main(String[] args) throws InterruptedException {
        SpringApplication.run(ProductApplication.class, args);
    }

}
