package com.workflow.pro.modules.sys.domain;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.workflow.pro.common.web.base.domain.BaseDomain;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.apache.ibatis.type.Alias;

import java.util.List;

/**
 * 数据字典模型
 *
 * Author: SOME
 * CreateTime: 2022/03/27
 * */
@Data
@Alias("SysDict")
@TableName("sys_dict")
@EqualsAndHashCode(callSuper = true)
public class SysDict extends BaseDomain {

    /**
     * 编号
     * */
    private String id;

    /**
     * 名称
     * */
    private String name;

    /**
     * 标识
     * */
    private String code;

    /**
     * 排序
     * */
    private String sort;
    /**
     * 状态
     * */
    @TableField("enable")
    private boolean enable;

    /**
     * 对应
     * */
    @TableField(exist = false)
    private List<SysDictData> children;

}
