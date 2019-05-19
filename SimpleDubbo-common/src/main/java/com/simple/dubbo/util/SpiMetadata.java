package com.simple.dubbo.util;

import java.lang.annotation.*;

/**
 * @author chengzhengzheng
 * @date 2019-05-18
 */
@Documented
@Retention(value = RetentionPolicy.RUNTIME)
@Target(value = ElementType.TYPE)
public @interface SpiMetadata {
    /**
     * 名称
     *
     * @return
     */
    String name() default "";

    /**
     * 优先级
     *
     * @return
     */
    int priority() default 0;
}
