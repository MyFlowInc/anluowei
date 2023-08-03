package com.workflow.pro.common.web.base.domain;

import com.baomidou.mybatisplus.annotation.TableField;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

/**
 * Base Tree 实体
 * <p>
 * Author: SOME
 * CreateTime: 2022/04/24
 */
@Data
public class TreeDomain2<T> {

    /**
     * 父级编号
     */
    @TableField("parent")
    private String parent;

    /**
     * 子级集合
     */
    @TableField(exist = false)
    private List<T> children = new ArrayList<>();
}
