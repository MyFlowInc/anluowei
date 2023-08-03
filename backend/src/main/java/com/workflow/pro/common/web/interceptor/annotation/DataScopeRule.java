package com.workflow.pro.common.web.interceptor.annotation;

import com.workflow.pro.common.web.interceptor.enums.Scope;
import com.workflow.pro.modules.sys.domain.SysRole;

import java.lang.annotation.*;

/**
 * 数据权限规则注解
 *
 * Author: Some
 * CreateTime: 2022/04/01
 * */
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.TYPE, ElementType.METHOD})
public @interface DataScopeRule {

    /**
     * 权 限 模 式
     *
     * {@link Scope} 权限 Scope
     * */
    Scope scope();

    /**
     * 角 色 模 式
     *
     * {@link SysRole} 角色 Code
     * */
    String role();
}
