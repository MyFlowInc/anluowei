package com.workflow.pro.common.secure.services;

import com.workflow.pro.modules.sys.domain.SysPower;
import com.workflow.pro.modules.sys.domain.SysRole;
import com.workflow.pro.modules.sys.domain.SysUser;
import com.workflow.pro.modules.sys.repository.SysUserRepository;
import com.workflow.pro.modules.sys.service.SysUserService;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import javax.annotation.Resource;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Security 用户服务实现
 *
 * Author: SOME
 * CreateTime: 2022/04/23
 * */
@Component
public class SecureUserService implements UserDetailsService {

    @Resource
    private SysUserRepository sysUserRepository;

    @Resource
    private SysUserService sysUserService;

    /**
     * 加载用户信息
     * */
    @Override
    public SecureUser loadUserByUsername(String username) throws UsernameNotFoundException {
        SysUser sysUser = sysUserRepository.selectByUsername(username);
        if(sysUser==null) {
            throw new UsernameNotFoundException("USERNAME NOT SUPPORT");
        }
        sysUser.setAuthorities(loadAuthorities(sysUser.getId()));
        sysUser.setRoles(loadRoles(sysUser.getId()));
        return sysUser;
    }

    public Set<? extends GrantedAuthority> loadAuthorities(String userId){
        Set<SimpleGrantedAuthority> authoritySet = new HashSet<>();
        List<SysPower> powers = sysUserService.power(userId);
        for (SysPower power : powers) {
            SimpleGrantedAuthority authority = new SimpleGrantedAuthority(power.getCode());
            authoritySet.add(authority);
        }
        return authoritySet;
    }

    public List<SysRole> loadRoles(String userId) {
        return sysUserService.role(userId);
    }
}
