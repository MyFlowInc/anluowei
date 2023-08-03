package com.workflow.pro.modules.cms.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.workflow.pro.common.web.base.page.PageResponse;
import com.workflow.pro.modules.cms.param.AppRequest;
import com.workflow.pro.modules.qywx.entity.AppInfo;

import java.util.List;

public interface IAppInfoService extends IService<AppInfo> {

    /**
     * 获取列表
     *
     * @param request 查询参数
     * @return {@link AppInfo}
     */
    List<AppInfo> list(AppRequest request);

    /**
     * @param request 查询参数 分页
     * @return {@link AppInfo}
     */
    PageResponse<AppInfo> page(AppRequest request);

}
