package com.workflow.pro.modules.sys.domain;

import com.baomidou.mybatisplus.annotation.TableName;
import com.workflow.pro.common.web.base.domain.BaseDomain;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.apache.ibatis.type.Alias;

/**
 * 数据字典模型
 *
 * Author: SOME
 * CreateTime: 2022/03/27
 * */
@Data
@Alias("SysDictData")
@TableName("sys_dict_data")
@EqualsAndHashCode(callSuper = true)
public class SysDictData extends BaseDomain {

    /**
     * 编号
     * */
    private String id;

    /**
     * 标签
     * */
    private String label;

    /**
     * 值
     * */
    private String value;

    /**
     * 启用
     * */
    private boolean enable;

    /**
     * 标识
     * */
    private String code;

    /**
     * 排序
     * */
    private String sort;

}
