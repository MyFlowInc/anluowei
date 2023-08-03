package com.workflow.pro.modules.sys.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.workflow.pro.modules.sys.domain.SysUserRole;
import com.workflow.pro.modules.sys.repository.SysUserRoleRepository;
import com.workflow.pro.modules.sys.service.SysUserRoleService;
import org.springframework.stereotype.Service;

@Service
public class SysUserRoleServiceImpl extends ServiceImpl<SysUserRoleRepository, SysUserRole> implements SysUserRoleService {
}
