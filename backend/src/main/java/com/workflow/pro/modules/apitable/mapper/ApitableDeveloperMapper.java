package com.workflow.pro.modules.apitable.mapper;

import org.apache.ibatis.annotations.Mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import com.workflow.pro.modules.apitable.param.ApitableDeveloperRequest;
import com.workflow.pro.modules.apitable.domain.ApitableDeveloper;
import org.apache.ibatis.annotations.Param;

import java.util.List;



/**
 * 格协作者Mapper接口
 *
 * @author some
 * @date 2023-06-21
 */
@Mapper
public interface ApitableDeveloperMapper extends BaseMapper<ApitableDeveloper> {
    /**
     * 查询格协作者
     *
     * @param id 格协作者ID
     * @return 格协作者
     */
        ApitableDeveloper selectApitableDeveloperById(String id);

    /**
     * 查询格协作者列表
     *

     * @return 格协作者集合
     */
    List<ApitableDeveloper> list(@Param("request") ApitableDeveloperRequest request);

    /**
     * 查询格协作者分頁列表
     *

     * @return 格协作者集合
     */
    List<ApitableDeveloper> selectApitableDeveloperList(@Param("request") ApitableDeveloperRequest request);


    /**
     * 新增格协作者
     *
     * @param apitableDeveloper 格协作者
     * @return 结果
     */
    int insertApitableDeveloper(ApitableDeveloper apitableDeveloper);

    /**
     * 修改格协作者
     *
     * @param apitableDeveloper 格协作者
     * @return 结果
     */
    int updateApitableDeveloper(ApitableDeveloper apitableDeveloper);

    /**
     * 删除格协作者
     *
     * @param id 格协作者ID
     * @return 结果
     */
    int deleteApitableDeveloperById(String id);

    /**
     * 批量删除格协作者
     *
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    int deleteApitableDeveloperByIds(String[] ids);

}
