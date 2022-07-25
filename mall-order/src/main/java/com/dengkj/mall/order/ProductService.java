package com.dengkj.mall.order;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;

/**
 * @author dengkj
 * @time 2022-02-21 15:49:35
 * @description
 */
@FeignClient(value = "mall-product", fallback = ProductFallbackService.class)
public interface ProductService {

    @PostMapping("/mall/product/subStock")
    String subStock();

}
