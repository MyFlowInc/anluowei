package com.workflow.pro.modules.cms.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.workflow.pro.common.web.base.page.PageResponse;
import com.workflow.pro.common.web.base.page.Pageable;
import com.workflow.pro.modules.cms.domain.SysActivityUser;
import com.workflow.pro.modules.cms.mapper.SysActivityUserMapper;
import com.workflow.pro.modules.cms.param.SysActivityUserRequest;
import com.workflow.pro.modules.cms.service.ISysActivityUserService;
import com.workflow.pro.modules.sys.domain.SysDept;
import com.workflow.pro.modules.sys.domain.SysUser;
import com.workflow.pro.modules.sys.service.SysDeptService;
import com.workflow.pro.modules.sys.service.SysUserService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class SysActivityUserServiceImpl extends ServiceImpl<SysActivityUserMapper, SysActivityUser> implements ISysActivityUserService {


    @Resource
    SysActivityUserMapper sysActivityUserMapper;

    @Resource
    SysDeptService sysDeptService;
    @Resource
    SysUserService sysUserService;

    @Override
    public List<SysActivityUser> list(SysActivityUserRequest request) {

        return sysActivityUserMapper.selectSysActivityUserList(request);
    }

    @Override
    public PageResponse<SysActivityUser> page(SysActivityUserRequest request) {
        List<SysActivityUser> sysActivityUserList = sysActivityUserMapper.selectSysActivityUserList(request);
        List< SysDept> sysDeptList =sysDeptService.list();
        List<SysUser> sysUserList=sysUserService.list();
        Map<String, Object> departIdNameIndex = new HashMap<>();
        Map<String ,Object> userIdMap =  new HashMap<>();
        for (SysDept s:sysDeptList) {
            departIdNameIndex.put(s.getId(),s.getName());
        }
        for (SysUser s:sysUserList) {
            userIdMap.put(s.getId(),s.getDeptId());
        }
        if (sysActivityUserList.size() > 0) {
            for (SysActivityUser user : sysActivityUserList) {
              //  SysUser userInfo = sysUserService.getOne(new QueryWrapper<SysUser>().eq("id", user.getUserId()));
                String getDeptId= (String) userIdMap.get(user.getUserId());
                if (getDeptId!= null) {
                    String DepartName= (String) departIdNameIndex.get(getDeptId);
                   // SysDept dept = sysDeptService.getOne(new QueryWrapper<SysDept>().eq("id", userInfo.getDeptId()));
                    if (DepartName != null) {
                        user.setActivityUserDeptName(DepartName);
                    }
                }else {
                    user.setActivityUserDeptName("无");
                }
            }

        }

        return Pageable.of(request, sysActivityUserList);
    }

    @Override
    public SysActivityUser add(SysActivityUser sysActivityUser) {
        QueryWrapper wrapper = new QueryWrapper();
        wrapper.eq("user_id", sysActivityUser.getUserId());
        wrapper.eq("activity_id", sysActivityUser.getActivityId());
        SysActivityUser one = this.getOne(wrapper);
        if (one != null) {
            return one;
        }
        this.save(sysActivityUser);
        SysActivityUser one1 = this.getOne(wrapper);
        return one1;
    }


}