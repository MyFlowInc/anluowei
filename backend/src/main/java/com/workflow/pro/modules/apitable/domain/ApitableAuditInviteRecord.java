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
 * 邀请同意记录对象 apitable_audit_invite_record
 *
 * @author some
 * @date 2023-06-21
 */


@Alias("ApitableAuditInviteRecord")
@TableName("apitable_audit_invite_record")
@EqualsAndHashCode(callSuper = true)
@Data
public class ApitableAuditInviteRecord extends BaseDomain    {
private static final long serialVersionUID=1L;

        /** Primary Key */
        
                /**  @TableId(value = "id", type = IdType.AUTO)*/
        @TableField(value = "id")
                        private String id;
        
            /** 空间id */
                                        
                        private String spaceId;
        
            /** 表格id */
                                        
                        private String dstId;
        
            /** 邀请id */
                                        
                        private String inviter;
        
            /** 成员id */
                                        
                        private String accepter;
        
            /** 0: 邀请; 1: 文件导出; 2: 邀请链接 */
                                        
                        private Integer type;
        
            /** 内容 */
                                        
                        private String content;
        
            /** 排序 */
                                        
                        private Long sort;
        
            /** 忽略消息 */
                                        
                        private Long ignoreMsg;
        
                        /** $column.columnComment */
                                        
                        
                    /** 租户编号 */
                                        
                        private String tenantId;
        
            }
