package com.workflow.pro.modules.sys.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.workflow.pro.common.web.base.page.PageResponse;
import com.workflow.pro.modules.sys.domain.SysDataSource;
import com.workflow.pro.modules.sys.param.SysDataSourceRequest;

import java.util.List;

public interface SysDataSourceService extends IService<SysDataSource> {

    /**
     * 获取数据源列表
     *
     * @param request 查询参数
     *
     * @return {@link SysDataSource}
     * */
    List<SysDataSource> list(SysDataSourceRequest request);

    /**
     * 获取数据源列表 (分页)
     *
     * @param request 查询参数
     *
     * @return {@link SysDataSource}
     * */
    PageResponse<SysDataSource> page(SysDataSourceRequest request);

}
