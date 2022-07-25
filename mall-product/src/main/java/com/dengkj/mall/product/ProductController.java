package com.dengkj.mall.product;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Random;

/**
 * @author dengkj
 * @time 2022-02-18 17:22:40
 * @description
 */
@RestController
@RequestMapping("/mall/product")
public class ProductController {

    @PostMapping("/subStock")
    public String subStock() {
        int rand = new Random().nextInt(100);
        if(rand % 2 == 0){
            throw new RuntimeException("接口降级测试");
        }
        return "减库存成功";
    }

}
