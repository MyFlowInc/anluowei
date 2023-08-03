package com.workflow.pro.modules.cms.param;

import com.workflow.pro.common.web.base.page.PageRequest;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class CmsCategoryRequest extends PageRequest {

    /**
     * 名称
     */
    private String categoryTitle;


    /**
     * 名称
     */
    private String orderBy;
}
