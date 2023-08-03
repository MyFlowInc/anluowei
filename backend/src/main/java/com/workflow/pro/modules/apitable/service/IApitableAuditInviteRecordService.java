package com.workflow.pro.modules.apitable.service;

import java.util.List;


import com.baomidou.mybatisplus.extension.service.IService;
import com.workflow.pro.common.web.base.page.PageResponse;
import com.workflow.pro.common.web.base.page.Pageable;


import com.workflow.pro.modules.apitable.param.ApitableAuditInviteRecordRequest;
import com.workflow.pro.modules.apitable.domain.ApitableAuditInviteRecord;

/**
 * 邀请同意记录IService接口
 *
 * @author some
 * @date 2023-06-21
 */
public interface IApitableAuditInviteRecordService  extends IService <ApitableAuditInviteRecord>{
    /**
     * 查询邀请同意记录
     *
     * @param id 邀请同意记录ID
     * @return 邀请同意记录
     */
        ApitableAuditInviteRecord selectApitableAuditInviteRecordById(String id);


    /**
     * 查询邀请同意记录
     * @param  request pageSize pageNum
     * @return 邀请同意记录 分页集合
     * */
    PageResponse<ApitableAuditInviteRecord> page(ApitableAuditInviteRecordRequest request);

    /**
     * 查询邀请同意记录列表
     * @return 邀请同意记录集合
     */
    List<ApitableAuditInviteRecord> list(ApitableAuditInviteRecordRequest request);

    /**
     * 新增邀请同意记录
     *
     * @param apitableAuditInviteRecord 邀请同意记录
     * @return 结果
     */
    int insertApitableAuditInviteRecord(ApitableAuditInviteRecord apitableAuditInviteRecord);

    /**
     * 修改邀请同意记录
     *
     * @param apitableAuditInviteRecord 邀请同意记录
     * @return 结果
     */
    int updateApitableAuditInviteRecord(ApitableAuditInviteRecord apitableAuditInviteRecord);

    /**
     * 批量删除邀请同意记录
     *
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    int deleteApitableAuditInviteRecordByIds(String[] ids);

    /**
     * 删除邀请同意记录信息
     *
     * @param id 邀请同意记录ID
     * @return 结果
     */
    int deleteApitableAuditInviteRecordById(String id);

}
