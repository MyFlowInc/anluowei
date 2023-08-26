package com.workflow.pro.modules.sys.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.workflow.pro.common.web.base.page.PageResponse;
import com.workflow.pro.modules.apitable.exception.BusinessExceptionNew;
import com.workflow.pro.modules.sys.domain.SysDept;
import com.workflow.pro.modules.sys.domain.SysPower;
import com.workflow.pro.modules.sys.domain.SysRole;
import com.workflow.pro.modules.sys.domain.SysUser;
import com.workflow.pro.modules.sys.param.LoginSuccess;
import com.workflow.pro.modules.sys.param.SysUserRequest;
import com.workflow.pro.modules.sys.param.SysUserResetPassword;

import java.util.List;

/**
 * 用户服务
 *
 * Author: SOME
 * CreateTime: 2022/03/28
 * */
public interface SysUserService extends IService<SysUser> {

    /**
     * 获取用户角色
     *
     * @param userId 用户编号
     *
     * @return {@link SysRole}
     * */
    List<SysRole> role(String userId);

    /**
     * 获取用户列表
     *
     * @param request 参数实体
     *
     * @return {@link SysUser}
     * */
    List<SysUser> list(SysUserRequest request);

    /**
     * 获取用户列表 (分页)
     *
     * @param request 查询参数
     *
     * @return {@link SysUser}
     * */
    PageResponse<SysUser> page(SysUserRequest request);

    /**
     * 获取用户菜单
     *
     * @param userId 用户编号
     *
     * @return {@link SysUser}
     * */
    List<SysPower> menu(String userId);

    /**
     * 获取用户权限
     *
     * @param userId 用户编号
     *
     * @return {@link SysPower}
     */
    List<SysPower> power(String userId);

    /**
     * 分配用户角色
     *
     * @param userId 用户编号
     * @param roleIds 角色编号
     *
     * @return {@link Boolean}
     * */
    Boolean give(String userId, List<String> roleIds);

    /**
     * 获取用户部门 (数据权限)
     *
     * @param userId 用户编号
     *
     * @return {@link SysDept}
     * */
    List<SysDept> dept(String userId);

    /**
     * 检测用户存在
     *
     * @param username 用户名
     *
     * @return {@link Boolean}
     * */
    Boolean exist(String username);

    /**
     * 修改密码
     *
     * @param userId 用户编号
     * @param password 密码实体
     *
     * @return {@link Boolean}
     * */
    Boolean editPassword(String userId, String password);

    /**
     * 重置密码
     *
     * @param userId 用户编号
     *
     * @return {@link Boolean}
     * */
    Boolean resetPassword(String userId);

    /**
     * 密码比较
     *
     * @param userId 用户编号
     * @param password 密码
     *
     * @return {@link Boolean}
     * */
    Boolean contrastPassword(String userId, String password);

    /**
     * 同步微信用户
     * @return {@link Boolean}
     * */
    Boolean syncWxUserInfo();

    String loginByWeixin(String code, String encryptedData, String iv);

    LoginSuccess loginByEmail(String pwd, String email) throws BusinessExceptionNew;

    /**
     * 重置密码
     * @return {@link Boolean}
     * */
    SysUserResetPassword resetPasswordMail(SysUserResetPassword pass) throws BusinessExceptionNew;
}
