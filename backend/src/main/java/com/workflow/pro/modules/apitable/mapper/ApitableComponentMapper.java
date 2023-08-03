package com.workflow.pro.modules.apitable.mapper;

import org.apache.ibatis.annotations.Mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import com.workflow.pro.modules.apitable.param.ApitableComponentRequest;
import com.workflow.pro.modules.apitable.domain.ApitableComponent;
import org.apache.ibatis.annotations.Param;

import java.util.List;



/**
 * 格组件Mapper接口
 *
 * @author some
 * @date 2023-05-22
 */
@Mapper
public interface ApitableComponentMapper extends BaseMapper<ApitableComponent> {
    /**
     * 查询格组件
     *
     * @param id 格组件ID
     * @return 格组件
     */
        ApitableComponent selectApitableComponentById(String id);

    /**
     * 查询格组件列表
     *

     * @return 格组件集合
     */
    List<ApitableComponent> list(@Param("request") ApitableComponentRequest request);

    /**
     * 查询格组件分頁列表
     *

     * @return 格组件集合
     */
    List<ApitableComponent> selectApitableComponentList(@Param("request") ApitableComponentRequest request);


    /**
     * 新增格组件
     *
     * @param apitableComponent 格组件
     * @return 结果
     */
    int insertApitableComponent(ApitableComponent apitableComponent);

    /**
     * 修改格组件
     *
     * @param apitableComponent 格组件
     * @return 结果
     */
    int updateApitableComponent(ApitableComponent apitableComponent);

    /**
     * 删除格组件
     *
     * @param id 格组件ID
     * @return 结果
     */
    int deleteApitableComponentById(String id);

    /**
     * 批量删除格组件
     *
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    int deleteApitableComponentByIds(String[] ids);

}
