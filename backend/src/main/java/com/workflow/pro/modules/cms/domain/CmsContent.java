package com.workflow.pro.modules.cms.domain;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.util.Date;

/**
 * 文章实体
 *
 * @author Some
 * @date 2022-08-05
 */
@Data
@TableName("cms_content")
public class CmsContent {

    /** null */
    @TableId
    private String id;

    /** 所属栏目 */
    private String categoryId;

    /** 文章内容 */
    private String contentDetails;

    /** 文章标题 */
    private String contentTitle;

    /** 文章跳转链接地址 */
    private String contentUrl;

    /** 关键字 */
    private String contentKeyword;

    /** 描述 */
    private String contentDescription;

    /** 文章缩略图 */
    private String contentImg;

    /** 文章来源 */
    private String contentSource;

    /** 文章作者 */
    private String contentAuthor;

    /** 是否显示0显示 1不显示 */
    private String contentDisplay;

    /** 文章类型 */
    private String contentType;

    /** 发布时间 */
    private Date contentDatetime;

    /** 自定义顺序 */
    private Long contentSort;

    /** 点击次数 */
    private Long contentHit;

    /** 修改时间 */
    private Date updateDate;

    /** 创建时间 */
    private Date createDate;

    /** 删除标记 */
    private Integer del;
    /** 删除标记 */
    private String isHome;

    private String contentBannerImg;


    /**
     * 部门名称 (忽略)
     * */
    @TableField(exist = false)
    private String cateTitle;
}
