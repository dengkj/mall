package com.dengkj.mall.auth;

import org.springframework.stereotype.Component;

/**
 * @author dengkj
 * @time 2022-02-21 15:56:12
 * @description
 */
@Component
public class UserFallbackService implements UserService {

    @Override
    public User getOne(String userName) {
        return null;
    }
}
