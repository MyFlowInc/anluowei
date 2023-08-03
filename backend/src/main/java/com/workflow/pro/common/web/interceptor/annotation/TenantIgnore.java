package com.workflow.pro.common.web.interceptor.annotation;

import java.lang.annotation.*;

/**
 * 忽略多租户
 *
 * Author: Some
 * CreateTime: 2022/04/01
 * */
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.TYPE, ElementType.METHOD})
public @interface TenantIgnore {

}
