package com.workflow.pro.modules.not.param;

import com.workflow.pro.common.web.base.page.PageRequest;
import lombok.Data;

/**
 * 公告列表 -- 参数实体
 * <p>
 * author: Some
 * date: 2022-04-01
 */
@Data
public class SysAnnounceUserRequest extends PageRequest {

    /**
     * 公告标题
     * */
    private String title;

}
