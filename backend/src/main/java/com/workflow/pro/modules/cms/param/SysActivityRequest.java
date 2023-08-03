package com.workflow.pro.modules.cms.param;

import com.workflow.pro.common.web.base.page.PageRequest;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class SysActivityRequest extends PageRequest {
    private String name;
}
