package com.workflow.pro.modules.not.param;

import com.workflow.pro.common.web.base.page.PageRequest;
import lombok.Data;
import org.apache.ibatis.type.Alias;

/**
 * 私信列表 -- 参数实体
 * <p>
 * author: Some
 * date: 2022-04-01
 */
@Data
@Alias("SysInboxRequest")
public class SysInboxRequest extends PageRequest {

    private String content;
}
