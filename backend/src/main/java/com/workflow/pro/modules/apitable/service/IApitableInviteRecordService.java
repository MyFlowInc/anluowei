package com.workflow.pro.modules.apitable.service;

import java.util.List;


import com.baomidou.mybatisplus.extension.service.IService;
import com.workflow.pro.common.web.base.page.PageResponse;
import com.workflow.pro.common.web.base.page.Pageable;


import com.workflow.pro.modules.apitable.param.ApitableInviteRecordRequest;
import com.workflow.pro.modules.apitable.domain.ApitableInviteRecord;

/**
 * 邀请同意记录IService接口
 *
 * @author some
 * @date 2023-06-30
 */
public interface IApitableInviteRecordService  extends IService <ApitableInviteRecord>{
    /**
     * 查询邀请同意记录
     *
     * @param id 邀请同意记录ID
     * @return 邀请同意记录
     */
        ApitableInviteRecord selectApitableInviteRecordById(String id);


    /**
     * 查询邀请同意记录
     * @param  request pageSize pageNum
     * @return 邀请同意记录 分页集合
     * */
    PageResponse<ApitableInviteRecord> page(ApitableInviteRecordRequest request);

    /**
     * 查询邀请同意记录列表
     * @return 邀请同意记录集合
     */
    List<ApitableInviteRecord> list(ApitableInviteRecordRequest request);

    /**
     * 新增邀请同意记录
     *
     * @param apitableInviteRecord 邀请同意记录
     * @return 结果
     */
    int insertApitableInviteRecord(ApitableInviteRecord apitableInviteRecord);

    /**
     * 修改邀请同意记录
     *
     * @param apitableInviteRecord 邀请同意记录
     * @return 结果
     */
    int updateApitableInviteRecord(ApitableInviteRecord apitableInviteRecord);

    /**
     * 批量删除邀请同意记录
     *
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    int deleteApitableInviteRecordByIds(String[] ids);

    /**
     * 删除邀请同意记录信息
     *
     * @param id 邀请同意记录ID
     * @return 结果
     */
    int deleteApitableInviteRecordById(String id);



}
