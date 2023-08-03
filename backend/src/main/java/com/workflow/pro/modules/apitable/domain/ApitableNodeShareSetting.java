package com.workflow.pro.modules.apitable.domain;


import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;

import lombok.Data;
import com.workflow.pro.common.web.base.domain.BaseDomain;
import com.baomidou.mybatisplus.annotation.TableName;
import org.apache.ibatis.type.Alias;
import lombok.EqualsAndHashCode;
import java.util.Date;

/**
 * 格基础设置对象 apitable_node_share_setting
 *
 * @author some
 * @date 2023-06-14
 */


@Alias("ApitableNodeShareSetting")
@TableName("apitable_node_share_setting")
@EqualsAndHashCode(callSuper = true)
@Data
public class ApitableNodeShareSetting extends BaseDomain    {
private static final long serialVersionUID=1L;

        /** Primary Key */
        
                /**  @TableId(value = "id", type = IdType.AUTO)*/
        @TableField(value = "id")
                        private String id;
        
            /** 数据id */
                                        
                        private String dstId;
        
            /** 节点id */
                                        
                        private String nodeId;
        
            /** 视图id */
                                        
                        private String viewId;
        
            /** 分享标识 */
                                        
                        private String shareId;
        
            /** 分享状态 (0: off, 1: on) */
                                        
                        private Integer isEnabled;
        
            /** 允许保存 (0: No, 1: Yes) */
                                        
                        private Integer allowSave;
        
            /** 允许编辑(0: No, 1: Yes) */
                                        
                        private Integer allowEdit;
        
            /** 分享属性 */
                                        
                        private String props;
        
            /** 排序 */
                                        
                        private Long sort;
        
                                /** 租户编号 */
                                        
                        private String tenantId;
        
            }
