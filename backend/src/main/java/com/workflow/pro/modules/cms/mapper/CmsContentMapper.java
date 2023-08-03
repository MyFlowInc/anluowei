package com.workflow.pro.modules.cms.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.workflow.pro.modules.cms.domain.CmsContent;
import com.workflow.pro.modules.cms.param.CmsContentRequest;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 文章Mapper接口
 *
 * @author Some
 * @date 2022-08-05
 */
@Mapper
public interface CmsContentMapper extends BaseMapper<CmsContent> {
    /**
     * 查询文章列表
     *
     * @param request 文章
     * @return 文章集合
     */
    List<CmsContent> selectCmsContentList(@Param("request") CmsContentRequest request);

}
