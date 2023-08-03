package com.workflow.pro.modules.apitable.param;

import com.workflow.pro.common.web.base.page.PageRequest;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.math.BigDecimal;
import java.util.Date;
/**
 * 会员等级Request参数请求接口
 *
 * @author some
 * @date 2023-06-02
 */

@Data
@EqualsAndHashCode(callSuper = true)
public class SysUserGradeRequest  extends PageRequest  {

        /** id */
            private String id;

            /** 等级 */
                                            private Integer grade;

            /** 等级名称 */
                                            private String name;

            /** 有效期 */
                                            private Long validDay;

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
                                            private Integer deleted;

                                /** 租户编号 */
                                            private String tenantId;

    }