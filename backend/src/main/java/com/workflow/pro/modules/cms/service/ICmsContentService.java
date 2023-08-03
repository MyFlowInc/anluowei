package com.workflow.pro.modules.cms.service;

import java.util.List;

import com.workflow.pro.common.web.base.page.PageResponse;
import com.workflow.pro.modules.cms.param.CmsContentRequest;
import com.workflow.pro.modules.cms.domain.CmsContent;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * 文章Service接口
 *
 * @author Some
 * @date 2022-08-05
 */
public interface ICmsContentService extends IService<CmsContent> {

    /**
     * 获取列表
     *
     * @param request 查询参数
     * @return {@link CmsContent}
     */
    List<CmsContent> list(CmsContentRequest request);

    /**
     * @param request 查询参数 分页
     * @return {@link CmsContent}
     */
    PageResponse<CmsContent> page(CmsContentRequest request);


    /**
     * 查询分类树形结构列表
     */
    List<CmsContent> getRecent();

}
