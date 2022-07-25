package com.dengkj.mall.auth;

/**
 * @author dengkj
 * @time 2022-07-15 17:36:06
 * @description
 */

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(value = "mall-user", fallback = UserFallbackService.class)
public interface UserService {

    // https://stackoverflow.com/questions/44313482/feign-client-with-spring-boot-requestparam-value-was-empty-on-parameter-0
    @GetMapping("/mall/user/getOne")
    User getOne(@RequestParam("userName") String userName);

}
