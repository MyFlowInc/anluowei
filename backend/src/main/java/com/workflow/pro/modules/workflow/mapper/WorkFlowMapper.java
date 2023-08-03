package com.workflow.pro.modules.workflow.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.workflow.pro.modules.workflow.domain.WorkFlow;
import com.workflow.pro.modules.workflow.param.FlowRequest;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
/**
 * 工作流主表Mapper接口
 *
 * @author some
 * @date 2022-11-16
 */
@Mapper
public interface WorkFlowMapper extends BaseMapper<WorkFlow> {
    /**
     * 查询工作流主表列表
     *
     * @param workFlow 工作流主表
     * @return 工作流主表集合
     */
    List<WorkFlow> selectWorkFlowList(@Param("request") FlowRequest request);

}
