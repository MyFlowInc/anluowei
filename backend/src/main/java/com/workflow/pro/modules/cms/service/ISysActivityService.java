package com.workflow.pro.modules.cms.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.workflow.pro.common.web.base.page.PageResponse;
import com.workflow.pro.modules.cms.domain.CmsContent;
import com.workflow.pro.modules.cms.domain.SysActivity;
import com.workflow.pro.modules.cms.param.SysActivityRequest;

import java.util.List;

public interface ISysActivityService extends IService<SysActivity> {

    /**
     * 获取列表
     *
     * @param request 查询参数
     * @return {@link CmsContent}
     */
    List<SysActivity> list(SysActivityRequest request);

    /**
     * @param request 查询参数 分页
     * @return {@link CmsContent}
     */
    PageResponse<SysActivity> page(SysActivityRequest request);

}
