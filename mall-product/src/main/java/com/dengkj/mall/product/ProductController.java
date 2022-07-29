package com.dengkj.mall.product;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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

    private static Logger log = LoggerFactory.getLogger(ProductController.class);

    @PostMapping("/subStock")
    public String subStock() {

        log.info("Handling subStock");

        int rand = new Random().nextInt(100);
        if(rand % 2 == 0){
            log.error("接口降级测试");
            throw new RuntimeException("接口降级测试");
        }
        return "减库存成功";
    }

}
