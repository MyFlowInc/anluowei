package com.workflow.pro.modules.generate.param;

import com.workflow.pro.common.web.base.page.PageRequest;
import lombok.Data;
import org.apache.ibatis.type.Alias;

@Data
@Alias("GenTableRequest")
public class GenTableRequest extends PageRequest {

    private String tableName;

    private String tableComment;
}
