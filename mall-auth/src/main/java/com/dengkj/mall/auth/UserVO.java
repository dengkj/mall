package com.dengkj.mall.auth;

import lombok.Data;

/**
 * @author dengkj
 * @time 2022-07-15 17:20:18
 * @description
 */
@Data
public class UserVO {

    private Integer userId;

    private String userName;

    private Byte auth;

    private String email;

}
