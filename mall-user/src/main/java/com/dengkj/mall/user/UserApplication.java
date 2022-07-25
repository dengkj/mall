package com.dengkj.mall.user;

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
public class UserApplication {

    public static void main(String[] args) throws InterruptedException {
        SpringApplication.run(UserApplication.class, args);
    }

}
