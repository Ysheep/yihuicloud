package com.qzdsoft.utils.excel.util;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**@author hehaiwen
 * 自定义字段名
 * @since 2017/2/23
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.METHOD, ElementType.FIELD, ElementType.PARAMETER})
public @interface FieldName {
    /**
     * 字段名
     */
    String value() default "";
    /**
     * 是否忽略
     */
    boolean Ignore() default false;
}
