package com.workflow.pro.modules.cms.param;

import com.workflow.pro.common.web.base.page.PageRequest;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class CmsContentRequest extends PageRequest {
    /**
     * 文章id
     * */
    private String categoryId;
    /**
     * 文章id
     * */
    private String contentTitle;

    private String isHome;

    private String docId;
}
