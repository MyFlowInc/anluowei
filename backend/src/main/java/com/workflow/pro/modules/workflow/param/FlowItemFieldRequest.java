package com.workflow.pro.modules.workflow.param;

import com.workflow.pro.common.web.base.page.PageRequest;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class FlowItemFieldRequest extends PageRequest {
    private String name;
    private int flowItemId;
}
