package com.workflow.pro.modules.sys.rest;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.workflow.pro.common.aop.annotation.Log;
import com.workflow.pro.common.constant.ControllerConstant;
import com.workflow.pro.common.context.UserContext;
import com.workflow.pro.common.web.base.BaseController;
import com.workflow.pro.common.web.domain.Result;
import com.workflow.pro.modules.apitable.domain.SysCaptcha;
import com.workflow.pro.modules.apitable.domain.SysUserGrade;
import com.workflow.pro.modules.apitable.exception.BusinessExceptionNew;
import com.workflow.pro.modules.apitable.service.ISysCaptchaService;
import com.workflow.pro.modules.apitable.service.ISysUserGradeService;
import com.workflow.pro.modules.sys.domain.SysUser;
import com.workflow.pro.modules.sys.param.LoginSuccess;
import com.workflow.pro.modules.sys.param.SysUserPasswordRequest;
import com.workflow.pro.modules.sys.param.SysUserRequest;
import com.workflow.pro.modules.sys.param.SysUserRoleRequest;
import com.workflow.pro.modules.sys.service.SysUserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

import java.util.List;
import java.util.Objects;

/**
 * 用户控制器
 * <p>
 * Author: SOME
 * CreateTime: 2022/03/28
 */
@Api(tags = { "用户" })
@RestController
@RequestMapping(ControllerConstant.PREFIX_SYS + "user")
public class SysUserController extends BaseController {

    @Resource
    private SysUserService sysUserService;

    @Resource
    private ISysUserGradeService sysUserGradeService;

    @Resource
    private UserContext userContext;

    @Resource
    private PasswordEncoder passwordEncoder;

    /**
     * 新增用户
     *
     * @param sysUser 用户实体
     */
    @PostMapping("save")
    @Log(title = "用户新增")
    @ApiOperation(value = "用户新增")
    public Result save(@RequestBody SysUser sysUser) {
        if (sysUserService.exist(sysUser.getUsername())) {
            return Result.success("已存在");
        }
        String password = sysUser.getPassword();
        String encodePassword = passwordEncoder.encode(password);
        sysUser.setPassword(encodePassword);
        sysUser.setLocked(false);
        return auto(sysUserService.save(sysUser));
    }

    /**
     * 修改用户
     *
     * @param sysUser 用户实体
     */
    @PutMapping("edit")
    @Log(title = "用户修改")
    @ApiOperation(value = "用户修改")
    public Result edit(@RequestBody SysUser sysUser) {
        return auto(sysUserService.updateById(sysUser));
    }

    /**
     * 修改密码
     *
     * @param request 参数实体
     */
    @PutMapping("password/edit")
    @Log(title = "修改密码")
    @ApiOperation(value = "修改密码")
    public Result editPassword(@RequestBody SysUserPasswordRequest request) {
        String userId = request.getUserId();
        String newPassword = request.getNewPassword();
        String oldPassword = request.getOldPassword();
        if (!sysUserService.contrastPassword(userId, oldPassword)) {
            return failure("密码不正确");
        }
        return auto(sysUserService.editPassword(userId, newPassword));
    }

    /**
     * 重置密码
     *
     * @param
     */
    @PutMapping("password/reset")
    @Log(title = "重置密码")
    @ApiOperation(value = "重置密码")
    public Result resetPassword(String id) {
        return auto(sysUserService.resetPassword(id));
    }

    /**
     * 查询用户
     *
     * @param request 查询参数
     */
    @GetMapping("page")
    @Log(title = "用户列表")
    @ApiOperation(value = "用户列表")
    public Result page(SysUserRequest request) {
        return success(sysUserService.page(request));
    }

    /**
     * 查询用户
     *
     * @param request 查询参数
     */
    @GetMapping("list")
    @Log(title = "用户列表")
    @ApiOperation(value = "用户列表")
    public Result list(SysUserRequest request) {
        return success(sysUserService.list(request));
    }

    /**
     * 分配角色
     *
     * @param request 参数实体
     */
    @PostMapping("give")
    @Log(title = "分配角色")
    @ApiOperation(value = "分配角色")
    public Result give(@RequestBody SysUserRoleRequest request) {
        return success(sysUserService.give(request.getUserId(), request.getRoleIds()));
    }

    /**
     * 删除用户
     *
     * @param id 用户编号
     */
    @DeleteMapping("remove")
    @Log(title = "用户删除")
    @ApiOperation(value = "用户删除")
    public Result remove(@RequestParam String id) {
        return auto(sysUserService.removeById(id));
    }

    /**
     * 批量删除
     *
     * @param ids 用户编号
     */
    @DeleteMapping("removeBatch")
    @Log(title = "批量删除")
    @ApiOperation(value = "批量删除")
    public Result removeBatch(@RequestParam List<String> ids) {
        return auto(sysUserService.removeByIds(ids));
    }

    /**
     * 个人资料
     */
    @GetMapping("profile")
    @Log(title = "个人资料")
    @ApiOperation(value = "个人资料")
    public Result profile() {
        SysUser byId = sysUserService.getById(userContext.getPrincipal().getId());
        if (!Objects.equals(byId.getGradeId(), "") && byId.getGradeId() != null) {
            SysUserGrade byId1 = sysUserGradeService.getById(byId.getGradeId());
            byId.setGradeName(byId1.getName());
        }
        return success(byId);
    }

    /**
     * 用户详情
     *
     * @param userId 用户编号
     */
    @GetMapping("info")
    @Log(title = "用户详情")
    @ApiOperation(value = "用户详情")
    public Result info(String userId) {
        return success(sysUserService.getById(userId));
    }

    /**
     * 获取用户菜单
     *
     * @return {@link Result}
     */
    @GetMapping("menu")
    @ApiOperation(value = "用户菜单")
    public Result menu() {
        return success(sysUserService.menu(userContext.getUserId()));
    }

    /**
     * 获取用户角色
     *
     * @param userId 用户编号
     */
    @GetMapping("role")
    @ApiOperation(value = "用户角色")
    public Result role(String userId) {
        return success(sysUserService.role(userId));
    }

    /**
     * 获取用户权限
     */
    @GetMapping("power")
    @ApiOperation(value = "用户权限")
    public Result power() {
        return success(sysUserService.power(userContext.getUserId()));
    }

    /**
     * 获取用户部门
     */
    @GetMapping("dept")
    @ApiOperation(value = "用户部门")
    public Result dept() {
        return success();
    }

    /**
     * 同步用户信息
     */
    @GetMapping("syncWxUser")
    @ApiOperation(value = "同步微信用户")
    public Result syncWxUser() {
        return success(sysUserService.syncWxUserInfo());
    }

    /**
     * 新增用户
     *
     * @param sysUser 用户实体
     */
    @PostMapping("register")
    @ApiOperation(value = "注册")
    public Result register(@RequestBody SysUser sysUser) {

        if (sysUser.getUsername() == null) {
            return Result.failure("用户名必传", null);
        }
        if (sysUser.getPhone() == null) {
            return Result.failure("手机号必传", null);
        }
        if (sysUser.getPassword() == null) {
            return Result.failure("密码必传", null);
        }
        Boolean exist = sysUserService.exist(sysUser.getUsername());
        if (exist) {
            return Result.failure("用户名已存在", null);
        }
        String password = sysUser.getPassword();
        String encodePassword = passwordEncoder.encode(password);
        sysUser.setPassword(encodePassword);
        sysUser.setLocked(false);
        sysUser.setEnable(true);
        return auto(sysUserService.save(sysUser));
    }

    @Resource
    private ISysCaptchaService sysCaptchaService;

    /**
     * 新增用户
     *
     * @param sysUser 用户实体
     */
    @PostMapping("register_email")
    @ApiOperation(value = "注册")
    public Result register_email(@RequestBody SysUser sysUser) throws BusinessExceptionNew {
        if (sysUser.getEmail() == null) {
            return Result.failure("邮箱必传", null);
        }
        if (sysUser.getPassword() == null) {
            return Result.failure("密码必传", null);
        }
        if (sysUser.getCode() == null) {
            return Result.failure("邮箱验证码必传", null);
        }
        sysUser.setFromAgent("web");
        try {
            long exist = sysUserService.count(new QueryWrapper<SysUser>().eq("email", sysUser.getEmail()));
            if (exist > 0) {
                return Result.failure("邮箱已经注册", null);
            }
        }
        catch (Exception e) {
        }
        //code校验
        try {
            sysCaptchaService.Check(sysUser.getEmail(), sysUser.getCode());
        }
        catch (BusinessExceptionNew e) {
            return Result.failure(e.getCode(), e.getMessage());
        }
        String password = sysUser.getPassword();
        String encodePassword = passwordEncoder.encode(password);
        sysUser.setPassword(encodePassword);
        sysUser.setLocked(false);
        sysUser.setEnable(true);
        sysUser.setAvatar("https://somebuckets3.s3.ap-northeast-1.amazonaws.com/1663580060169-default_handsome.jpg");
        return auto(sysUserService.save(sysUser));
    }

    /**
     * 同步用户信息
     */
    @GetMapping("login_email")
    @ApiOperation(value = "email登录")
    public Result login_email(@RequestParam("password") String password, @RequestParam("email") String email) {
        try {
            LoginSuccess success = sysUserService.loginByEmail(password, email);
            return success(success);
        }
        catch (BusinessExceptionNew e) {
            return Result.failure(e.getCode(), e.getMessage());
        }
    }
}