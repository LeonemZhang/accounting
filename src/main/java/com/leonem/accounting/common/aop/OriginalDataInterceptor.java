package com.leonem.accounting.common.aop;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import java.lang.reflect.Method;

@Component
@Slf4j
public class OriginalDataInterceptor implements HandlerInterceptor {
    public static final String RESPONSE_DATA_ANN = "RESPONSE_DATA_ANN";

    /**
     * 在请求处理之前进行调用（Controller方法调用之前）
     * 识别注解OriginalData
     */
    @Override
    public boolean preHandle(@NotNull HttpServletRequest request, @NotNull HttpServletResponse response, @NotNull Object handler) {
        if (handler instanceof final HandlerMethod handlerMethod) {
            final Class<?> clazz = handlerMethod.getBeanType();
            final Method method = handlerMethod.getMethod();
            if (clazz.isAnnotationPresent(OriginalData.class) || method.isAnnotationPresent(OriginalData.class)) {
                request.setAttribute(RESPONSE_DATA_ANN, clazz.getAnnotation(OriginalData.class) == null
                        ? method.getAnnotation(OriginalData.class) : clazz.getAnnotation(OriginalData.class));
            }
        }
        return true;
    }

    @Override
    public void postHandle(@NotNull HttpServletRequest request, @NotNull HttpServletResponse response, @NotNull Object handler, ModelAndView modelAndView) throws Exception {
        HandlerInterceptor.super.postHandle(request, response, handler, modelAndView);
    }

    @Override
    public void afterCompletion(@NotNull HttpServletRequest request, @NotNull HttpServletResponse response, @NotNull Object handler, Exception ex) throws Exception {
        HandlerInterceptor.super.afterCompletion(request, response, handler, ex);
    }
}
