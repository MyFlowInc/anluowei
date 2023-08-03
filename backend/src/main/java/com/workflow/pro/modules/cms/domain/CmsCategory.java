package com.workflow.pro.modules.cms.domain;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.workflow.pro.common.web.base.domain.TreeDomain2;
import lombok.Data;

import java.util.Date;

/**
 * 分类实体
 *
 * @author Some
 * @date 2022-08-05
 */
@Data
@TableName("cms_category")
public class    CmsCategory extends TreeDomain2 {

    /**
     * null
     */
    @TableId("id")
    private String id;

    /**
     * 所属栏目
     */
    @TableField("category_id")
    private Long categoryId;

    /**
     * 是否是叶子节点
     */
    @TableField("leaf")
    private Long leaf;

    /**
     * 顶级id
     */
    @TableField("top_id")
    private Long topId;

    /**
     * 字典对应编号
     */
    @TableField("dict_id")
    private Long dictId;

    /**
     * 栏目管理的内容模型id
     */
    @TableField("mdiy_model_id")
    private Long mdiyModelId;

    /**
     * 父类型编号，多个id逗号
     */
    @TableField("parent")
    private String parent;

    /**
     * 栏目管理名称
     */
    @TableField("category_title")
    private String categoryTitle;

    /**
     * 缩略图
     */
    @TableField("category_img")
    private String categoryImg;

    /**
     * 栏目属性
     */
    @TableField("category_flag")
    private String categoryFlag;

    /**
     * 栏目管理描述
     */
    @TableField("category_descrip")
    private String categoryDescrip;

    /**
     * 栏目管理关键字
     */
    @TableField("category_keyword")
    private String categoryKeyword;

    /**
     * 栏目路径
     */
    @TableField("category_path")
    private String categoryPath;

    /**
     * 自定义链接
     */
    @TableField("category_diy_url")
    private String categoryDiyUrl;

    /**
     * 内容模板
     */
    @TableField("category_url")
    private String categoryUrl;

    /**
     * 列表模板
     */
    @TableField("category_list_url")
    private String categoryListUrl;
    /**
     * 内容模板
     */
    @TableField("category_banner")
    private String categoryBanner;
    /**
     * 栏目管理属性
     */
    @TableField("category_type")
    private String categoryType;

    /**
     * 栏目拼音
     */
    @TableField("category_pinyin")
    private String categoryPinyin;

    /**
     * 自定义顺序
     */
    @TableField("category_sort")
    private Long categorySort;

    /**
     * 自定义顺序
     */
    @TableField("category_hit")
    private Long categoryHit;


    /**
     * 修改时间
     */
    @TableField("update_date")
    private Date updateDate;

    /**
     * 创建时间
     */
    @TableField("create_date")
    private Date createDate;

    /**
     * 删除标记
     */
    @TableField("del")
    private Long del;
}
