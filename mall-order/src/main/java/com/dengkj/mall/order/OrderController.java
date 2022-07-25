package com.dengkj.mall.order;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @author dengkj
 * @time 2022-02-18 17:58:39
 * @description
 */
@RestController
@RequestMapping("/mall/order")
public class OrderController {

    @Resource
    private ProductService productService;

    @PostMapping("/order")
    public String order() {
        String result = productService.subStock();
        return result;
    }

}
