package com.workflow.pro.modules.sys.param;

import lombok.Data;
import org.apache.ibatis.type.Alias;

/**
 * 权限列表 -- 参数实体
 *
 * Author: SOME
 * CreateTime: 2022/04/01
 */
@Data
@Alias("SysPowerRequest")
public class SysPowerRequest {

    /**
     * 权 限 名 称
     * */
    private String name;

}
