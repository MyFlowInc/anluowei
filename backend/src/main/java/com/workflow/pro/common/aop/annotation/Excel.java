package com.workflow.pro.common.aop.annotation;

import com.workflow.pro.common.aop.enums.Model;

import java.lang.annotation.*;

/**
 * Excel 注解
 *
 * Author SOME
 * CreateTime: 2022/04/20
 * */
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.TYPE, ElementType.METHOD})
public @interface Excel {

    Class clazz();

    Model model() default Model.WRITE;

}
