package com.ai.art.common.annotation;

import java.lang.annotation.*;

@Retention(RetentionPolicy.RUNTIME)
@Target({ ElementType.METHOD })
@Documented
public @interface PrintLog {

    /**
     * 日志描述信息
     * @return
     **/
    String description() default "";

}
