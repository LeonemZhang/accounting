package com.leonem.accounting.module.user.controller;

import com.leonem.accounting.module.user.pojo.UserLoginReq;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author 张宗涵
 * @date 2023/9/20
 */
@Tag(name = "用户登录接口")
@RequestMapping("/auth")
@RestController
@RequiredArgsConstructor
public class LoginController {
    @Operation(summary = "用户登录")
    @PostMapping("/login")
    public Object userLogin(@RequestBody UserLoginReq req) {
        return null;
    }
}
