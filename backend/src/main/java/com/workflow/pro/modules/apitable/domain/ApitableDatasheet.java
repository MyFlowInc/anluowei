package com.workflow.pro.modules.apitable.domain;


import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;

import com.workflow.pro.modules.sys.domain.SysUser;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import com.workflow.pro.common.web.base.domain.BaseDomain;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.NoArgsConstructor;
import org.apache.ibatis.type.Alias;
import lombok.EqualsAndHashCode;

import java.util.Date;

/**
 * 数据格对象 apitable_datasheet
 *
 * @author some
 * @date 2023-05-22
 */


@Alias("ApitableDatasheet")
@Builder(toBuilder = true)
@AllArgsConstructor
@NoArgsConstructor
@TableName("apitable_datasheet")
@EqualsAndHashCode(callSuper = true)
@Data
public class ApitableDatasheet extends BaseDomain {
    private static final long serialVersionUID = 1L;

    /**  @TableId(value = "id", type = IdType.AUTO)*/
    @TableField(value = "id")
    private String id;


    /** Custom ID */

    private String dstId;

    /** 节点id */

    private String nodeId;

    /** 表格名 */

    private String dstName;

    /** 工作空间 */

    private String spaceId;

    /** 版本号 */

    private Long revision;

    /** 删除表示(0:No,1:Yes) */

    /**
     * 是否启用
     * */
    @TableField("archive")
    private Integer archive;


    /**
     * 是否启用
     * */
    @TableField("enable")
    private boolean enable;

    /** 排序 */

    private Long sort;

    /** 租户编号 */

    private String tenantId;

    @TableField(exist = false)

    ApitableDeveloper shareSetting;


    @TableField(exist = false)

    private Boolean isCreator = false;


    @TableField(exist = false)

    private Boolean isDeveloper = false;


    /**
     * 子级集合
     */
    @TableField(exist = false)
    private SysUser createUserInfo;
}
