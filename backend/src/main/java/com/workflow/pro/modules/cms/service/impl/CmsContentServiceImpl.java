package com.workflow.pro.modules.cms.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.workflow.pro.common.web.base.page.PageResponse;
import com.workflow.pro.common.web.base.page.Pageable;
import com.workflow.pro.modules.cms.domain.CmsContent;
import com.workflow.pro.modules.cms.mapper.CmsContentMapper;
import com.workflow.pro.modules.cms.param.CmsContentRequest;
import com.workflow.pro.modules.cms.service.ICmsContentService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Objects;

/**
 * 文章Service业务层处理
 *
 * @author Some
 * @date 2022-08-05
 */
@Service
public class CmsContentServiceImpl extends ServiceImpl<CmsContentMapper, CmsContent> implements ICmsContentService {


    @Resource
    CmsContentMapper cmsContentMapper;

    /**
     * 查询文章
     *
     * @param request 文章
     * @return 文章 分页集合
     */
    @Override
    public List<CmsContent> list(CmsContentRequest request) {
        return cmsContentMapper.selectCmsContentList(request);

    }

    @Override
    public PageResponse<CmsContent> page(CmsContentRequest request) {

        if (request.getCategoryId() != null && !Objects.equals(request.getCategoryId(), "")) {
            //阅读数量加+1
            List<CmsContent> list = cmsContentMapper.selectCmsContentList(request);
            if (list.size() > 0) {
                for (CmsContent s : list) {
                    Long a = s.getContentHit() + 1;
                    s.setContentHit(a);
                    this.updateById(s);
                }
            }
            ;
        }

        return Pageable.of(request, (() -> cmsContentMapper.selectCmsContentList(request)));

    }

    @Override
    public List<CmsContent> getRecent() {
        QueryWrapper wrapper = new QueryWrapper();
        wrapper.orderBy(true, false, "content_hit");
        List<CmsContent> CmsContent_list = this.list(wrapper);
        return CmsContent_list;
    }
}
