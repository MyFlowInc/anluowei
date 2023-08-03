package com.workflow.pro.modules.cms.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.workflow.pro.common.web.base.page.PageResponse;
import com.workflow.pro.modules.cms.domain.CmsContent;
import com.workflow.pro.modules.cms.domain.SysActivityUser;
import com.workflow.pro.modules.cms.param.SysActivityUserRequest;

import java.util.List;

public interface ISysActivityUserService extends IService<SysActivityUser> {

    /**
     * 获取列表
     *
     * @param request 查询参数
     * @return {@link CmsContent}
     */
    List<SysActivityUser> list(SysActivityUserRequest request);

    /**
     * @param request 查询参数 分页
     * @return {@link CmsContent}
     */
    PageResponse<SysActivityUser> page(SysActivityUserRequest request);


    /**
     * @return {@link CmsContent}
     */
    SysActivityUser add(SysActivityUser sysActivityUser);
}
