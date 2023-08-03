package com.workflow.pro.modules.generate.param;

import lombok.Data;

@Data
public class GenTableImportRequest {

    /**
     * 表格名称
     * */
    private String[] tableNames;
}
