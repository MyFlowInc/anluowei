package com.workflow.pro.modules.workflow.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.workflow.pro.modules.workflow.domain.WorkFlowField;
import com.workflow.pro.modules.workflow.param.FlowFieldRequest;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 1Mapper接口
 *
 * @author Some
 * @date 2022-11-16
 */
@Mapper
public interface WorkFlowFieldMapper extends BaseMapper<WorkFlowField> {
    /**
     * 查询1列表
     *
     * @param workFlowField 1
     * @return 1集合
     */
    List<WorkFlowField> selectWorkFlowFieldList(@Param("request") FlowFieldRequest request);

}
