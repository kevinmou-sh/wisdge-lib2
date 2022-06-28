package com.wisdge.poi;

import java.lang.annotation.*;

@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface ExportEntityMap {
    String EnName() default "数据库列名";
    String CnName() default "实体映射名";
    boolean primarykey() default false;
    boolean required() default false;
}
