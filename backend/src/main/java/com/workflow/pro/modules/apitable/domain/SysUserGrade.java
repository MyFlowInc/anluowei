package com.workflow.pro.modules.apitable.domain;


import com.baomidou.mybatisplus.annotation.TableField;

import io.swagger.models.auth.In;
import lombok.Data;
import com.workflow.pro.common.web.base.domain.BaseDomain;
import com.baomidou.mybatisplus.annotation.TableName;
import org.apache.ibatis.type.Alias;
import lombok.EqualsAndHashCode;

import java.math.BigDecimal;

/**
 * 会员等级对象 sys_user_grade
 *
 * @author some
 * @date 2023-06-02
 */


@Alias("SysUserGrade")
@TableName("sys_user_grade")
@EqualsAndHashCode(callSuper = true)
@Data
public class SysUserGrade extends BaseDomain    {
private static final long serialVersionUID=1L;

        /** id */
        
                /**  @TableId(value = "id", type = IdType.AUTO)*/
        @TableField(value = "id")
                        private String id;
        
            /** 等级 */
                                        
                        private Integer grade;
        
            /** 等级名称 */
                                        
                        private String name;
        
            /** 有效期 */
                                        
                        private Integer validDay;
        
            /** 价格 */
                                        
                        private BigDecimal price;
        
            /** 展示图 */
                                        
                        private String img;
        
            /** 促销价格 */
                                        
                        private BigDecimal promotionPrice;
        
            /** 绑定角色 */
                                        
                        private String bindRole;
        
            /** 状态 */
                                        
                        private String status;
        
            /** 销量 */
                                        
                        private Long sale;
        
            /** 升级会员等级条件描述 */
                                        
                        private String catchCondition;
        
            /** 升级会员等级条件，init:默认获取;pay:付费升级；frequency:消费次数；amount:累积消费金额升级 */
                                        
                        private String catchType;
        
            /** 达到升级条件的值 */
                                        
                        private Long catchValue;
        
            /** 会员权益描述 */
                                        
                        private String userPrivilege;
        
            /** 享受折扣 */
                                        
                        private BigDecimal discount;
        
            /** 积分加速 */
                                        
                        private BigDecimal speed;
        
            /** 排序 */
                                        
                        private Long sort;
        
            /** 删除表示(0:No,1:Yes) */
                                        
                        
                                /** 租户编号 */
                                        
                        private String tenantId;
        
            }
