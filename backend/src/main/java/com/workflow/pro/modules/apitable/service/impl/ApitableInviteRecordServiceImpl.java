package com.workflow.pro.modules.apitable.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.workflow.pro.common.web.base.page.PageResponse;
import com.workflow.pro.common.web.base.page.Pageable;


import com.workflow.pro.modules.apitable.mapper.ApitableInviteRecordMapper;
import com.workflow.pro.modules.apitable.domain.ApitableInviteRecord;
import com.workflow.pro.modules.apitable.param.ApitableInviteRecordRequest;
import com.workflow.pro.modules.apitable.service.IApitableInviteRecordService;
import com.workflow.pro.modules.sys.domain.SysUser;
import com.workflow.pro.modules.sys.service.SysUserService;


import org.springframework.stereotype.Service;

import javax.annotation.Resource;

import java.util.List;


/**
 * 邀请同意记录ServiceImpl业务层处理
 *
 * @author some
 * @date 2023-06-30
 */
@Service
public class ApitableInviteRecordServiceImpl extends ServiceImpl<ApitableInviteRecordMapper, ApitableInviteRecord> implements IApitableInviteRecordService {

    @Resource
    ApitableInviteRecordMapper apitableInviteRecordMapper;


    /**
     * 查询邀请同意记录
     *
     * @param id 邀请同意记录ID
     * @return 邀请同意记录
     */
    @Override
    public ApitableInviteRecord selectApitableInviteRecordById(String id) {
        return apitableInviteRecordMapper.selectApitableInviteRecordById(id);
    }

    /**
     * 查询邀请同意记录列表
     * @return 邀请同意记录
     */
    @Override
    public List<ApitableInviteRecord> list(ApitableInviteRecordRequest request) {
        return apitableInviteRecordMapper.list(request);
    }

    /**
     * 查询邀请同意记录
     * @return 邀请同意记录 分页集合
     * */
    @Override
    public PageResponse<ApitableInviteRecord> page(ApitableInviteRecordRequest request) {
        return Pageable.of(request, (() -> apitableInviteRecordMapper.selectApitableInviteRecordList(request)));
    }

    /**
     * 新增邀请同意记录
     *
     * @param apitableInviteRecord 邀请同意记录
     * @return 结果
     */

    @Override
    public int insertApitableInviteRecord(ApitableInviteRecord apitableInviteRecord) {
        return apitableInviteRecordMapper.insertApitableInviteRecord(apitableInviteRecord);
    }

    /**
     * 修改邀请同意记录
     *
     * @param apitableInviteRecord 邀请同意记录
     * @return 结果
     */
    @Override
    public int updateApitableInviteRecord(ApitableInviteRecord apitableInviteRecord) {
        return apitableInviteRecordMapper.updateApitableInviteRecord(apitableInviteRecord);
    }

    /**
     * 删除邀请同意记录对象
     *
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    @Override
    public int deleteApitableInviteRecordByIds(String[] ids) {
        return apitableInviteRecordMapper.deleteApitableInviteRecordByIds(ids);
    }

    /**
     * 删除邀请同意记录信息
     *
     * @param id 邀请同意记录ID
     * @return 结果
     */
    @Override
    public int deleteApitableInviteRecordById(String id) {
        return apitableInviteRecordMapper.deleteApitableInviteRecordById(id);
    }


}
