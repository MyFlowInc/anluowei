package com.workflow.pro.modules.sys.param;

import java.util.List;

import lombok.Data;

/**
 * 用户角色 -- 参数实体
 *
 * Author: SOME
 * CreateTime: 2022/04/01
 */
@Data
public class SysUserResetPassword {

    /**
     * 用户编号
     * */
    private String email;

    /**
     * 角色集合
     * */
    private String code;

    private String password;

}
