package com.workflow.pro.common.aop.annotation;

import com.workflow.pro.common.aop.enums.Action;

import java.lang.annotation.*;

/**
 * 日志 注解
 *
 * Author SOME
 * CreateTime: 2022/04/20
 * */
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.TYPE, ElementType.METHOD})
public @interface Log {

    /**
     * 默认无参输入
     */
    String value() default "暂无标题";
    /**
     * Title 默认输入
     * */
    String title() default "暂无标题";

    /**
     * Describe 默认输入
     * */
    String describe() default "暂无描述";

    /**
     * Action 操作类型
     * */
    Action action() default Action.QUERY;

}
