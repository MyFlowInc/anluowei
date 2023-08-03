package com.workflow.pro.modules.cms.param;

import com.workflow.pro.common.web.base.page.PageRequest;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class SysActivityUserRequest extends PageRequest {

    private String userId;
    private String userName;
    private String activityId;
    private String activityName;
}
