package com.dengkj.mall.auth;

import lombok.Data;

/**
 * @author dengkj
 * @time 2022-07-15 14:42:09
 * @description
 */
@Data
public class User {

    private Integer userId;

    private String userName;

    private String password;

    private Byte auth;

    private String email;

}
