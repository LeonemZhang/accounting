package com.leonem.accounting.module.user.pojo;

import lombok.Getter;
import lombok.Setter;

/**
 * @author 张宗涵
 * @date 2023/9/22
 */
@Getter
@Setter
public class UserLoginReq {
    /**
     * 用户名
     */
    private String username;

    /**
     * 密码
     */
    private String password;
}
