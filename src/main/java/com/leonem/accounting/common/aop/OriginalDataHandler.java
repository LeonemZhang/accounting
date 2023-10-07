package com.leonem.accounting.common.aop;

import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.jetbrains.annotations.NotNull;
import org.springframework.core.MethodParameter;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;


@Slf4j
@RestControllerAdvice(basePackages = "com.cn.wavetop.quality")
@RequiredArgsConstructor
public class OriginalDataHandler implements ResponseBodyAdvice<Object> {
    public static final String RESPONSE_DATA_ANN = "RESPONSE_DATA_ANN";
    private final HttpServletRequest request;

    /**
     * 检查是否使用了注解OriginalData
     */
    @Override
    public boolean supports(@NotNull MethodParameter returnType, @NotNull Class<? extends HttpMessageConverter<?>> converterType) {
        OriginalData originalData = (OriginalData) request.getAttribute(RESPONSE_DATA_ANN);
        return originalData == null;
    }

    /**
     * 如果没有使用注解则自动把返回值包装成ToCodeMessage类型
     */
    @Override
    public Object beforeBodyWrite(Object body, @NotNull MethodParameter returnType, @NotNull MediaType selectedContentType,
                                  @NotNull Class<? extends HttpMessageConverter<?>> selectedConverterType, @NotNull ServerHttpRequest request,
                                  @NotNull ServerHttpResponse response) {
        if (body instanceof HttpStatus) {
            return ResponseEntity.status((HttpStatus) body).body(null);
        } else if (body instanceof Throwable) {
            return ResponseEntity.badRequest();
        } else {
            return ResponseEntity.ok(body);
        }
    }
}
