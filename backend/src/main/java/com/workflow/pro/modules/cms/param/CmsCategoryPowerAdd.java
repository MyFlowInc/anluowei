package com.workflow.pro.modules.cms.param;

import com.workflow.pro.common.web.base.page.PageRequest;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

@Data
@EqualsAndHashCode(callSuper = true)
public class CmsCategoryPowerAdd extends PageRequest {
    /**
     * 名称
     */
    private String deptId;


    /**
     * 分类集合
     * */
    private List<String> categoryId;
}
