package com.workflow.pro.modules.sys.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.workflow.pro.common.secure.uutoken.SecureUserToken;
import com.workflow.pro.common.secure.uutoken.SecureUserTokenService;
import com.workflow.pro.common.tools.core.Httprequests;
import com.workflow.pro.common.web.base.page.PageResponse;
import com.workflow.pro.common.web.base.page.Pageable;
import com.workflow.pro.common.web.domain.Result;
import com.workflow.pro.common.web.domain.ResultCode;
import com.workflow.pro.modules.apitable.exception.BusinessExceptionNew;
import com.workflow.pro.modules.sys.domain.*;
import com.workflow.pro.modules.sys.param.LoginSuccess;
import com.workflow.pro.modules.sys.param.SysUserRequest;
import com.workflow.pro.modules.sys.repository.SysDeptRepository;
import com.workflow.pro.modules.sys.repository.SysPowerRepository;
import com.workflow.pro.modules.sys.repository.SysRoleRepository;
import com.workflow.pro.modules.sys.repository.SysUserRepository;
import com.workflow.pro.modules.sys.service.SysDeptService;
import com.workflow.pro.modules.sys.service.SysUserRoleService;
import com.workflow.pro.modules.sys.service.SysUserService;
import lombok.extern.slf4j.Slf4j;
import me.chanjar.weixin.common.error.WxErrorException;
import me.chanjar.weixin.cp.api.WxCpService;
import me.chanjar.weixin.cp.bean.WxCpUser;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

import java.io.Serializable;
import java.util.*;

@Slf4j
@Service
public class SysUserServiceImpl extends ServiceImpl<SysUserRepository, SysUser> implements SysUserService {

    @Resource
    private PasswordEncoder passwordEncoder;

    @Resource
    private SysRoleRepository sysRoleRepository;

    @Resource
    private SysUserRepository sysUserRepository;

    @Resource
    private SysDeptRepository sysDeptRepository;

    @Resource
    private SysPowerRepository sysPowerRepository;

    @Resource
    private SysUserRoleService sysUserRoleService;

    @Override
    public List<SysRole> role(String userId) {
        return sysRoleRepository.selectRoleByUserId(userId);
    }

    @Override
    public List<SysUser> list(SysUserRequest request) {
        return sysUserRepository.selectUser(request);
    }

    @Override
    public PageResponse<SysUser> page(SysUserRequest request) {
        return Pageable.of(request, (() -> sysUserRepository.selectUser(request)));
    }

    @Override
    public List<SysPower> power(String userId) {
        return sysPowerRepository.selectPowerByUserId(userId);
    }

    @Override
    public List<SysPower> menu(String userId) {
        return toTree(sysPowerRepository.selectMenu(userId), "0");
    }

    @Override
    @Transactional
    public Boolean give(String userId, List<String> roleIds) {
        sysUserRoleService.lambdaUpdate().eq(SysUserRole::getUserId, userId).remove();
        List<SysUserRole> userRoles = new ArrayList<>();
        roleIds.forEach(roleId -> {
            SysUserRole userRole = new SysUserRole();
            userRole.setUserId(userId);
            userRole.setRoleId(roleId);
            userRoles.add(userRole);
        });
        return sysUserRoleService.saveBatch(userRoles);
    }

    public List<SysPower> toTree(List<SysPower> sysMenus, String parent) {
        List<SysPower> list = new ArrayList<>();
        for (SysPower menu : sysMenus) {
            if (parent.equals(menu.getParent())) {
                menu.setChildren(toTree(sysMenus, menu.getId()));
                list.add(menu);
            }
        }
        return list;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean removeById(Serializable id) {
        sysUserRepository.deleteById(id);
        sysUserRoleService.lambdaUpdate().eq(SysUserRole::getUserId, id).remove();
        return true;
    }

    @Override
    public boolean removeByIds(Collection<? extends Serializable> idList) {
        idList.forEach(this::removeById);
        return true;
    }

    @Override
    public List<SysDept> dept(String userId) {
        return sysDeptRepository.selectDeptByUserId(userId);
    }

    @Override
    public Boolean exist(String username) {
        long count = this.lambdaQuery().eq(SysUser::getUsername, username).count();
        if (count > 0) {
            return true;
        }
        else {
            return false;
        }
    }

    @Override
    public Boolean editPassword(String userId, String password) {
        SysUser sysUser = new SysUser();
        sysUser.setId(userId);
        sysUser.setPassword(passwordEncoder.encode(password));
        sysUserRepository.updateById(sysUser);
        return true;
    }

    @Override
    public Boolean resetPassword(String userId) {
        SysUser sysUser = new SysUser();
        sysUser.setId(userId);
        sysUser.setPassword(passwordEncoder.encode("123456"));
        sysUserRepository.updateById(sysUser);
        return true;
    }

    @Override
    public Boolean contrastPassword(String userId, String password) {
        SysUser sysUser = this.getById(userId);
        if (passwordEncoder.matches(password, sysUser.getPassword())) {
            return true;
        }
        else {
            return false;
        }
    }

    @Autowired
    SysDeptService sysDeptService;

    @Autowired
    WxCpService wxCpService;

    @Override
    public Boolean syncWxUserInfo() {
        log.info("同步脚本");
        List<SysDept> SysDeptList = sysDeptService.list();
        for (SysDept sysDept : SysDeptList) {
            long id = Long.parseLong(sysDept.getId());
            try {
                List<WxCpUser> wxCpUsersList = wxCpService.getUserService().listByDepartment(id, true, 1);
                //0 男 1 女

                SysUser user = new SysUser();
                for (WxCpUser userInfo : wxCpUsersList) {
                    user.setId(userInfo.getUserId());

                    user.setUsername(userInfo.getUserId());

                    user.setNickname(userInfo.getAlias());

                    user.setDeptId(userInfo.getMainDepartment());
                    user.setLocked(false);
                    user.setEnable(true);
                    user.setEmail(userInfo.getEmail() == null ? "0" : userInfo.getEmail());
                    user.setAvatar(userInfo.getAvatar() == null ? "https://somebuckets3.s3.ap-northeast-1.amazonaws.com/1663580060169-default_handsome.jpg" : userInfo.getGender().getCode());
                    user.setPhone(userInfo.getTelephone() == null ? "" : userInfo.getTelephone());
                    if (userInfo.getGender() != null) {
                        String code = userInfo.getGender().getCode();
                        if (code.equals("1")) {
                            code = "0";
                        }
                        else if (code.equals("2")) {
                            code = "1";
                        }
                        user.setGender(code);
                    }
                    else {
                        user.setGender("2");
                    }
                    saveOrUpdate(user);
                }
            }
            catch (WxErrorException e) {
                e.printStackTrace();
            }
        }

        return null;
    }


    //发送	https://api.weixin.qq.com/sns/jscode2session?appid=APPID&secret=SECRET&js_code=JSCODE&grant_type=authorization_code 获取用户的openid和session_key
    //注意这个是 WeChatTool.wxspAppid 是微信小程序的appid 从微信小程序后台获取 WeChatTool.wxspSecret 这个也一样，我这里是用了常量来进行保存方便多次使用

    @Override
    public String loginByWeixin(String code, String encryptedData, String iv) {
        String wxspAppid = "wxe68e26ad219c06fe";
        String wxspSecret = "009fec7b97badb6d87232856394077ec";
        String url = "https://api.weixin.qq.com/sns/jscode2session";
        String params = "appid=" + wxspAppid + "&secret=" + wxspSecret + "&js_code=" + code + "&grant_type=authorization_code";
        String sendGet = Httprequests.sendGet(url, params); //发起请求拿到key和openid
        return sendGet;
    }


    @Resource
    private SecureUserTokenService customUserDetailsTokenService;

    @Override
    public LoginSuccess loginByEmail(String pwd, String email) throws BusinessExceptionNew {
        SysUser exist = getOne(new QueryWrapper<SysUser>().eq("email", email));
        if (exist == null || exist.getId() == null) {
            throw new BusinessExceptionNew(3011, "当前注册邮箱不存在");
        }
        if (!this.contrastPassword(exist.getId(), pwd)) {
            throw new BusinessExceptionNew(3012, "密码不正确");
        }
        SecureUserToken userToken = customUserDetailsTokenService.createToken(exist);
        String tokenKey = customUserDetailsTokenService.saveToken(userToken);
        String tokenValue = userToken.getToken();
        LoginSuccess success = new LoginSuccess();
        success.setToken(tokenValue);
        success.setTokenKey(tokenKey);
        return success;
    }
}
