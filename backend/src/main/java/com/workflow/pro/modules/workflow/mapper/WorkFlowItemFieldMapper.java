package com.workflow.pro.modules.workflow.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.workflow.pro.modules.workflow.domain.WorkFlowItemField;
import com.workflow.pro.modules.workflow.param.FlowItemFieldRequest;
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
public interface WorkFlowItemFieldMapper extends BaseMapper<WorkFlowItemField> {
    /**
     * 查询2列表
     *
     * @param workFlowItemField 2
     * @return 2集合
     */
    List<WorkFlowItemField> selectWorkFlowItemFieldList(@Param("request") FlowItemFieldRequest request);

}
