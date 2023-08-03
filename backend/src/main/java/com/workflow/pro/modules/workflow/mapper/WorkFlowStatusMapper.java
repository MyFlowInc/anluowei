package com.workflow.pro.modules.workflow.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.workflow.pro.modules.workflow.domain.WorkFlowStatus;
import com.workflow.pro.modules.workflow.param.FlowStatusRequest;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 2Mapper接口
 *
 * @author Some
 * @date 2022-11-16
 */
@Mapper
public interface WorkFlowStatusMapper extends BaseMapper<WorkFlowStatus> {
    /**
     * 查询2列表
     *
     * @param workFlowStatus 2
     * @return 2集合
     */
    List<WorkFlowStatus> selectWorkFlowStatusList(@Param("request") FlowStatusRequest request);

}
