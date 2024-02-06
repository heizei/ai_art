package com.ai.art.common.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.METHOD) // 注解的范围是类、接口、枚举的方法上
@Retention(RetentionPolicy.RUNTIME)
public @interface Encrypt {

    /**
     * 入参需要加密的字段
     */
    String[] paramFields() default {};

    /**
     * 响应参数需解密的字段
     */
    String[] respFields() default {};
}
