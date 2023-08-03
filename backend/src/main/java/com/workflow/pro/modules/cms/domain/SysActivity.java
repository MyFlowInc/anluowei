package com.workflow.pro.modules.cms.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.workflow.pro.common.web.base.domain.BaseDomain;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.apache.ibatis.type.Alias;

/**
 * activty实体
 *
 * @author Some
 * @date 2022-09-20
 */
@Data
@Alias("SysActivity")
@TableName("sys_activity")
@EqualsAndHashCode(callSuper = true)
public class SysActivity extends BaseDomain {

    private static final long serialVersionUID = 1L;
    @TableId(value = "id", type = IdType.AUTO)
    @TableField(value = "id")
    private int id;
    /**
     * name
     */
    @TableField("name")
    private String name;

    /**
     * img
     */
    @TableField("img")
    private String img;

    /**
     * img
     */
    @TableField("banner_img")
    private String bannerImg;
    /**
     * is_send
     */
    @TableField("send")
    private String send;

    /**
     * content
     */
    @TableField("content")
    private String Content;

    /**
     * content
     */
    @TableField("begin_time")
    private String beginTime;    /**
     * content
     */
    @TableField("end_time")
    private String endTime;

    /**
     * content
     */
    @TableField(exist = false)
    private String url;

    /**
     * content
     */
    @TableField(exist = false)
    private Long usersNu;

}