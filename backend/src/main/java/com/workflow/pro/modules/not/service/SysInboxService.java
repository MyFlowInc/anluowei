package com.workflow.pro.modules.not.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.workflow.pro.common.web.base.page.PageResponse;
import com.workflow.pro.modules.not.domain.SysInbox;
import com.workflow.pro.modules.not.param.SysInboxRequest;
import java.util.List;

public interface SysInboxService extends IService<SysInbox> {

    /**
     * 获取私信列表
     *
     * @param request 参数实体
     *
     * @return {@link SysInbox}
     * */
    List<SysInbox> list(SysInboxRequest request);

    /**
     * 获取私信列表 (分页)
     *
     * @param request 参数实体
     *
     * @return {@link SysInbox}
     * */
    PageResponse<SysInbox> page(SysInboxRequest request);

}
