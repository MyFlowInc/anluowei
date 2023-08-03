package com.workflow.pro.modules.cms.param;

import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = false)
@Data
public class OssNeed {
    private String suffix;
    private Long count;
}
