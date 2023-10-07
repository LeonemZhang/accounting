package com.leonem.accounting.common.aop;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.ElementType.TYPE;

/**
 * 返回值包装注解，使用此注解后，controller和service的返回值必须用ToCodeMessage包装，否则返回原始数据
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({TYPE, METHOD})
@Documented
public @interface OriginalData {
}
