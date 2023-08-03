package com.workflow.pro.modules.cms.param;

import com.workflow.pro.modules.cms.domain.CmsCategory;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;
@EqualsAndHashCode(callSuper = false)
@Data
public class CountNeed {
    public List<CmsCategory> listCms;
    public int count;
}
