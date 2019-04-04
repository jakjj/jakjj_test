package org.wz.config.annotation;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.util.ClassUtils;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;
import java.lang.reflect.Array;
import java.util.*;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

@Target({FIELD})
@Retention(RUNTIME)
@Documented
public @interface SignatureField {
    //签名顺序
    int order() default 0;

    //字段name自定义值
    String customName() default "";

    //字段value自定义值
    String customValue() default "";


}