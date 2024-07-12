package com.ljq.project.annotation;

import java.lang.annotation.*;

/**
 *
 * <p>
 * 登陆验证
 * </p>
 *
 * @author wlj
 * @since 2021/3/9
 */
@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface Authorized {
    String value() default "";
}
