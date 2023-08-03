package com.workflow.pro.modules.workflow.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.workflow.pro.modules.workflow.domain.WorkFlowItem;
import com.workflow.pro.modules.workflow.param.FlowItemRequest;
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
public interface WorkFlowItemMapper extends BaseMapper<WorkFlowItem> {
    /**
     * 查询2列表
     *
     * @param workFlowItem 2
     * @return 2集合
     */
    List<WorkFlowItem> selectWorkFlowItemList( @Param("request") FlowItemRequest request);

}
