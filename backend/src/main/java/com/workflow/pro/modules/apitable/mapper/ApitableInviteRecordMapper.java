package com.workflow.pro.modules.apitable.mapper;

import org.apache.ibatis.annotations.Mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import com.workflow.pro.modules.apitable.param.ApitableInviteRecordRequest;
import com.workflow.pro.modules.apitable.domain.ApitableInviteRecord;
import org.apache.ibatis.annotations.Param;

import java.util.List;



/**
 * 邀请同意记录Mapper接口
 *
 * @author some
 * @date 2023-06-30
 */
@Mapper
public interface ApitableInviteRecordMapper extends BaseMapper<ApitableInviteRecord> {
    /**
     * 查询邀请同意记录
     *
     * @param id 邀请同意记录ID
     * @return 邀请同意记录
     */
        ApitableInviteRecord selectApitableInviteRecordById(String id);

    /**
     * 查询邀请同意记录列表
     *

     * @return 邀请同意记录集合
     */
    List<ApitableInviteRecord> list(@Param("request") ApitableInviteRecordRequest request);

    /**
     * 查询邀请同意记录分頁列表
     *

     * @return 邀请同意记录集合
     */
    List<ApitableInviteRecord> selectApitableInviteRecordList(@Param("request") ApitableInviteRecordRequest request);


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
     * 删除邀请同意记录
     *
     * @param id 邀请同意记录ID
     * @return 结果
     */
    int deleteApitableInviteRecordById(String id);

    /**
     * 批量删除邀请同意记录
     *
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    int deleteApitableInviteRecordByIds(String[] ids);

}
