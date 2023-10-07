package com.leonem.accounting.common.aop;


import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import lombok.val;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

/**
 * controller切面，切入点为所有controller和所有带有@SaveOperationLog注解的方法
 * 记录入参，后面全局异常处理的打印中使用
 * 以及记录操作日志
 **/
@Aspect
@Slf4j
@Component
@RequiredArgsConstructor
public class AspectController {
    public static final String REQUEST_PARAM = "request_param";
    private final HttpServletRequest request;

    @Pointcut("execution(* com.leonem.accounting..*.*Controller.*(..))")
    public void inController() {
    }

    @Before("inController()")
    public void doBefore(JoinPoint point) {
        request.setAttribute(AspectController.REQUEST_PARAM, convertParam(point));
    }

    private Object convertParam(JoinPoint joinPoint) {
        for (val one : joinPoint.getArgs()) {
            if (one instanceof HttpServletRequest || one instanceof HttpServletResponse || one instanceof MultipartFile) {
                return request.getParameterMap();
            }
        }
        return joinPoint.getArgs();
    }
}
