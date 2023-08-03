package com.workflow.pro.modules.apitable.mapper;

import org.apache.ibatis.annotations.Mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import com.workflow.pro.modules.apitable.param.ApitableAuditInviteRecordRequest;
import com.workflow.pro.modules.apitable.domain.ApitableAuditInviteRecord;
import org.apache.ibatis.annotations.Param;

import java.util.List;



/**
 * 邀请同意记录Mapper接口
 *
 * @author some
 * @date 2023-06-21
 */
@Mapper
public interface ApitableAuditInviteRecordMapper extends BaseMapper<ApitableAuditInviteRecord> {
    /**
     * 查询邀请同意记录
     *
     * @param id 邀请同意记录ID
     * @return 邀请同意记录
     */
        ApitableAuditInviteRecord selectApitableAuditInviteRecordById(String id);

    /**
     * 查询邀请同意记录列表
     *

     * @return 邀请同意记录集合
     */
    List<ApitableAuditInviteRecord> list(@Param("request") ApitableAuditInviteRecordRequest request);

    /**
     * 查询邀请同意记录分頁列表
     *

     * @return 邀请同意记录集合
     */
    List<ApitableAuditInviteRecord> selectApitableAuditInviteRecordList(@Param("request") ApitableAuditInviteRecordRequest request);


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
     * 删除邀请同意记录
     *
     * @param id 邀请同意记录ID
     * @return 结果
     */
    int deleteApitableAuditInviteRecordById(String id);

    /**
     * 批量删除邀请同意记录
     *
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    int deleteApitableAuditInviteRecordByIds(String[] ids);

}
