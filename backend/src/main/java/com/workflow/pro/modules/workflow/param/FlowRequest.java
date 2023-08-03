package com.workflow.pro.modules.workflow.param;

import com.workflow.pro.common.context.BeanContext;
import com.workflow.pro.common.context.UserContext;
import com.workflow.pro.common.web.base.page.PageRequest;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class FlowRequest extends PageRequest {
    UserContext userContext = BeanContext.getBean(UserContext.class);
    private String userId = userContext.getUserId();
    private String name;
    private int statusId;
}
