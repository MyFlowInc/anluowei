package com.workflow.pro.modules.qywx.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import org.apache.ibatis.type.Alias;

import java.io.Serializable;

/**
 * <p>
 *
 * </p>
 *
 * @author chenlinhuan
 * @since 2021-07-07
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@Alias("AppInfo")
@TableName("app_info")
public class AppInfo implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @TableField("agent_id")
    private Integer agentId;

    @TableField("secret")
    private String secret;

    @TableField("name")
    private String name;

    @TableField("url")
    private String url;


}
