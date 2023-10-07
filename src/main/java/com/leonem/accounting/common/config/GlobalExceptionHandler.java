package com.leonem.accounting.common.config;

import cn.hutool.core.util.StrUtil;
import cn.hutool.json.JSONUtil;
import com.leonem.accounting.common.aop.AspectController;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * @author 张宗涵
 * @date 2023/9/20
 */
@Slf4j
@RestControllerAdvice
@RequiredArgsConstructor
public class GlobalExceptionHandler {
    private final HttpServletRequest request;

    @ExceptionHandler(value = Exception.class)
    @ResponseBody
    public ResponseEntity<String> exception(Exception e) {
        log.error("发生异常{}", ErrorAndReqMsg(), e);
        return ResponseEntity.badRequest().build();
    }

    private String getHostAndUrl() {
        return "RequestIP=" + request.getRemoteHost() + "  URL=" + request.getRequestURI();
    }

    private String ErrorAndReqMsg() {
        return StrUtil.format("\n{}  Req={}", getHostAndUrl(), JSONUtil.toJsonStr(request.getAttribute(AspectController.REQUEST_PARAM)));
    }
}
