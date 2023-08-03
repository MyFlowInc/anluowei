package com.workflow.pro.modules.cms.mapper;

import com.workflow.pro.modules.cms.param.CmsCategoryRequest;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

import com.workflow.pro.modules.cms.domain.CmsCategory;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

/**
 * 分类Mapper接口
 *
 * @author Some
 * @date 2022-08-05
 */
@Mapper
public interface CmsCategoryMapper extends BaseMapper<CmsCategory> {

    /**
     * 查询分类列表
     *
     * @param request 查询参数
     * @return {@link CmsCategory}
     */
    List<CmsCategory> selectCmsCategoryList(@Param("request") CmsCategoryRequest request);

    /**
     * 获取分类列表
     *
     * @return {@link CmsCategory}
     */
    List<CmsCategory> selectCate();

    /**
     * 根据 parentId 获取分类参数列表
     *
     * @param parentId 父级编号
     * @return {@link CmsCategory}
     */
    List<CmsCategory> selectCateByParentId(String parentId);


}
