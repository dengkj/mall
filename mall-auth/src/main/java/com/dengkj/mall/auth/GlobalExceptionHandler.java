package com.dengkj.mall.auth;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author dengkj
 * @time 2021-05-11 15:04:24
 * @description
 */
@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(value = Throwable.class)
    @ResponseBody
    public ResponseEntity<String> otherExceptionHandler(Throwable e) {
        e.printStackTrace();
        return ResponseEntity.ok(e.getMessage());
    }

}
