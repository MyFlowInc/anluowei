package com.workflow.pro.modules.apitable.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.workflow.pro.common.web.base.page.PageResponse;
import com.workflow.pro.common.web.base.page.Pageable;


import com.workflow.pro.modules.apitable.mapper.ApitableAuditInviteRecordMapper;
import com.workflow.pro.modules.apitable.domain.ApitableAuditInviteRecord;
import com.workflow.pro.modules.apitable.param.ApitableAuditInviteRecordRequest;
import com.workflow.pro.modules.apitable.service.IApitableAuditInviteRecordService;


import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import java.util.List;


/**
 * 邀请同意记录ServiceImpl业务层处理
 *
 * @author some
 * @date 2023-06-21
 */
@Service
public class ApitableAuditInviteRecordServiceImpl extends ServiceImpl<ApitableAuditInviteRecordMapper, ApitableAuditInviteRecord> implements IApitableAuditInviteRecordService {

    @Resource
    ApitableAuditInviteRecordMapper apitableAuditInviteRecordMapper;

    /**
     * 查询邀请同意记录
     *
     * @param id 邀请同意记录ID
     * @return 邀请同意记录
     */
    @Override
    public ApitableAuditInviteRecord selectApitableAuditInviteRecordById(String id) {
        return apitableAuditInviteRecordMapper.selectApitableAuditInviteRecordById(id);
    }

    /**
     * 查询邀请同意记录列表
     * @return 邀请同意记录
     */
    @Override
    public List<ApitableAuditInviteRecord> list (ApitableAuditInviteRecordRequest request){
        return apitableAuditInviteRecordMapper.list(request);
    }

    /**
     * 查询邀请同意记录
     * @return 邀请同意记录 分页集合
     * */
    @Override
    public PageResponse<ApitableAuditInviteRecord> page(ApitableAuditInviteRecordRequest request) {
        return Pageable.of(request, (() -> apitableAuditInviteRecordMapper.selectApitableAuditInviteRecordList(request)));
    }

    /**
     * 新增邀请同意记录
     *
     * @param apitableAuditInviteRecord 邀请同意记录
     * @return 结果
     */

    @Override
    public int insertApitableAuditInviteRecord(ApitableAuditInviteRecord apitableAuditInviteRecord) {
        return apitableAuditInviteRecordMapper.insertApitableAuditInviteRecord(apitableAuditInviteRecord);
    }

    /**
     * 修改邀请同意记录
     *
     * @param apitableAuditInviteRecord 邀请同意记录
     * @return 结果
     */
    @Override
    public int updateApitableAuditInviteRecord(ApitableAuditInviteRecord apitableAuditInviteRecord) {
        return apitableAuditInviteRecordMapper.updateApitableAuditInviteRecord(apitableAuditInviteRecord);
    }

    /**
     * 删除邀请同意记录对象
     *
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    @Override
    public int deleteApitableAuditInviteRecordByIds(String[] ids) {
        return apitableAuditInviteRecordMapper.deleteApitableAuditInviteRecordByIds(ids);
    }

    /**
     * 删除邀请同意记录信息
     *
     * @param id 邀请同意记录ID
     * @return 结果
     */
    @Override
    public int deleteApitableAuditInviteRecordById(String id) {
        return apitableAuditInviteRecordMapper.deleteApitableAuditInviteRecordById(id);
    }
}
