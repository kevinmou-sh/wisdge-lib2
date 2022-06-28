package com.wisdge.ezcell.annotation;

import java.lang.annotation.*;

/**
 * Field column num at excel head
 */
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@Inherited
public @interface ExcelColumnNo {

    /**
     * col num
     * @return
     */
    int value();

    /**
     *
     * Default @see com.alibaba.excel.util.TypeUtil
     * if default is not  meet you can set format
     *
     * @return
     */
    String format() default "";
}
