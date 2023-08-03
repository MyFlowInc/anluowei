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
 * 变更集收集对象 apitable_datasheet_changeset
 *
 * @author some
 * @date 2023-05-22
 */


@Alias("ApitableDatasheetChangeset")
@TableName("apitable_datasheet_changeset")
@EqualsAndHashCode(callSuper = true)
@Data
public class ApitableDatasheetChangeset extends BaseDomain    {
private static final long serialVersionUID=1L;

        /** Primary key */
        
                /**  @TableId(value = "id", type = IdType.AUTO)*/
        @TableField(value = "id")
                        private String id;
        
            /** 消息id */
                                        
                        private String messageId;
        
            /** 表格id */
                                        
                        private String dstId;
        
            /** 成员id */
                                        
                        private Long memberId;
        
            /** 操作动作集合 */
                                        
                        private String operations;
        
            /** 版本号 */
                                        
                        private Long revision;
        
            /** 删除表示(0:No,1:Yes) */
                                        
                        
            /** 排序 */
                                        
                        private Long sort;
        
                                /** 租户编号 */
                                        
                        private String tenantId;
        
            }
