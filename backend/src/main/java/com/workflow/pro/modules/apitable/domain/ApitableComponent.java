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
 * 格组件对象 apitable_component
 *
 * @author some
 * @date 2023-05-22
 */


@Alias("ApitableComponent")
@TableName("apitable_component")
@EqualsAndHashCode(callSuper = true)
@Data
public class ApitableComponent extends BaseDomain    {
private static final long serialVersionUID=1L;

        /** Primary key */
        
                /**  @TableId(value = "id", type = IdType.AUTO)*/
        @TableField(value = "id")
                        private String id;
        
            /** 名称 */
                                        
                        private String name;
        
            /** 英文名 */
                                        
                        private String nameEn;
        
            /** 组件内容 */
                                        
                        private String content;
        
            /** 工作空间 */
                                        
                        private String spaceId;
        
            /** 版本号 */
                                        
                        private Long revision;
        
            /** 排序 */
                                        
                        private Long sort;
        
            /** 删除表示(0:No,1:Yes) */
                                        
                        
                                /** 租户编号 */
                                        
                        private String tenantId;
        
            }
