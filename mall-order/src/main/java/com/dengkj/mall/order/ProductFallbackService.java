package com.dengkj.mall.order;

import org.springframework.stereotype.Component;

/**
 * @author dengkj
 * @time 2022-02-21 15:56:12
 * @description
 */
@Component
public class ProductFallbackService implements ProductService {

    @Override
    public String subStock() {
        return "服务降级";
    }
}
