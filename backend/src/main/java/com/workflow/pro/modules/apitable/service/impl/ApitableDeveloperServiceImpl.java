package com.workflow.pro.modules.apitable.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.workflow.pro.common.web.base.page.PageResponse;
import com.workflow.pro.common.web.base.page.Pageable;


import com.workflow.pro.modules.apitable.domain.ApitableDatasheet;
import com.workflow.pro.modules.apitable.domain.ApitableInviteRecord;
import com.workflow.pro.modules.apitable.exception.BusinessExceptionNew;
import com.workflow.pro.modules.apitable.mapper.ApitableDeveloperMapper;
import com.workflow.pro.modules.apitable.domain.ApitableDeveloper;
import com.workflow.pro.modules.apitable.mapper.ApitableInviteRecordMapper;
import com.workflow.pro.modules.apitable.param.ApitableDeveloperRequest;
import com.workflow.pro.modules.apitable.param.ApitableInviteRecordRequest;
import com.workflow.pro.modules.apitable.service.IApitableDeveloperService;
import com.workflow.pro.modules.qywx.entity.AppInfo;
import com.workflow.pro.modules.sys.domain.SysUser;
import com.workflow.pro.modules.sys.param.SysUserRequest;
import com.workflow.pro.modules.sys.service.SysUserService;


import org.springframework.stereotype.Service;

import javax.annotation.Resource;

import java.util.ArrayList;
import java.util.List;


/**
 * 格协作者ServiceImpl业务层处理
 *
 * @author some
 * @date 2023-06-21
 */
@Service
public class ApitableDeveloperServiceImpl extends ServiceImpl<ApitableDeveloperMapper, ApitableDeveloper> implements IApitableDeveloperService {

    @Resource
    ApitableDeveloperMapper apitableDeveloperMapper;



    @Resource
    private SysUserService sysUserService;

    @Resource
    ApitableInviteRecordMapper apitableInviteRecordMapper;

    /**
     * 查询格协作者
     *
     * @param id 格协作者ID
     * @return 格协作者
     */
    @Override
    public ApitableDeveloper selectApitableDeveloperById(String id) {
        return apitableDeveloperMapper.selectApitableDeveloperById(id);
    }

    /**
     * 查询格协作者列表
     * @return 格协作者
     */
    @Override
    public List<ApitableDeveloper> list(ApitableDeveloperRequest request) {
        return apitableDeveloperMapper.selectApitableDeveloperList(request);
    }

    /**
     * 查询格协作者
     * @return 格协作者 分页集合
     * */
    @Override
    public PageResponse<ApitableDeveloper> page(ApitableDeveloperRequest request) {

        PageResponse<ApitableDeveloper> of = Pageable.of(request, (() -> apitableDeveloperMapper.selectApitableDeveloperList(request)));
        List<ApitableDeveloper> list = new ArrayList<>();
        for (ApitableDeveloper apitableDeveloper : of.getRecord()) {
            SysUser byId = sysUserService.getById(apitableDeveloper.getUserId());
            if (byId != null) {
                byId.setPassword("");
                apitableDeveloper.setUserInfo(byId);
                list.add(apitableDeveloper);
            }
        }
        of.setRecord(list);
        return of;
    }

    /**
     * 新增格协作者
     *
     * @param apitableDeveloper 格协作者
     * @return 结果
     */

    @Override
    public int insertApitableDeveloper(ApitableDeveloper apitableDeveloper) {
        return apitableDeveloperMapper.insertApitableDeveloper(apitableDeveloper);
    }

    /**
     * 修改格协作者
     *
     * @param apitableDeveloper 格协作者
     * @return 结果
     */
    @Override
    public int updateApitableDeveloper(ApitableDeveloper apitableDeveloper) {
        return apitableDeveloperMapper.updateApitableDeveloper(apitableDeveloper);
    }

    /**
     * 删除格协作者对象
     *
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    @Override
    public int deleteApitableDeveloperByIds(String[] ids) {
        return apitableDeveloperMapper.deleteApitableDeveloperByIds(ids);
    }

    /**
     * 删除格协作者信息
     *
     * @param id 格协作者ID
     * @return 结果
     */
    @Override
    public int deleteApitableDeveloperById(String id) {
        return apitableDeveloperMapper.deleteApitableDeveloperById(id);
    }

    @Override
    public boolean enableDeveloper(String dstId, String userId) {

        SysUser byId = sysUserService.getById(userId);
        if (byId == null) {
            throw new BusinessExceptionNew(4001, "用户id传入错误");
        }

        UpdateWrapper<ApitableDeveloper> userUpdateWrapper = new UpdateWrapper<>();
        ApitableDeveloper apitableDeveloper = new ApitableDeveloper();
        ApitableInviteRecord apitableInviteRecord = new ApitableInviteRecord();

        //启用开发者
        apitableDeveloper.setEnable(1);
        userUpdateWrapper.eq("dst_id", dstId).eq("user_id", userId);
        UpdateWrapper<ApitableInviteRecord> msgUpdateWrapper = new UpdateWrapper<>();
        //忽略消息
        msgUpdateWrapper.eq("accepter", userId).eq("dst_id", dstId);
        apitableInviteRecord.setIgnoreMsg(1);

        apitableInviteRecordMapper.update(apitableInviteRecord, msgUpdateWrapper);
        apitableDeveloperMapper.update(apitableDeveloper, userUpdateWrapper);
        return true;
    }

    @Override
    public List<SysUser> userList(SysUserRequest req) {
        List<SysUser> newList = new ArrayList<>();
        List<SysUser> list = sysUserService.list(req);
        for (SysUser sysUser : list) {
            ApitableDeveloper developer = getOne(new QueryWrapper<ApitableDeveloper>().eq("user_id", sysUser.getId()).eq("dst_id", req.getDstId()), false);
            if (developer != null) {
                sysUser.setIsInvite(1);
            }
            newList.add(sysUser);
        }
        //根据 文章id 查询 已经邀请的用户 enable是否为1  为1 developer 为1
        return newList;
    }
}
