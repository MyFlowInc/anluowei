package com.workflow.pro.modules.apitable.domain;


import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;

import lombok.Data;
import com.workflow.pro.common.web.base.domain.BaseDomain;
import com.baomidou.mybatisplus.annotation.TableName;
import org.apache.ibatis.type.Alias;
import lombok.EqualsAndHashCode;

import java.math.BigDecimal;

/**
 * 订单中所包含的商品对象 oms_order_item
 *
 * @author some
 * @date 2023-06-13
 */


@Alias("OmsOrderItem")
@TableName("oms_order_item")
@EqualsAndHashCode(callSuper = true)
@Data
public class OmsOrderItem extends BaseDomain    {
private static final long serialVersionUID=1L;

        /** 订单id */
        
                /**  @TableId(value = "id", type = IdType.AUTO)*/
        @TableField(value = "id")
                        private String id;
        
            /** 订单id */
                                        
                        private String orderId;
        
            /** 订单编号 */
                                        
                        private String orderSn;
        
            /** $column.columnComment */
                                        
                        private String productId;
        
            /** $column.columnComment */
                                        
                        private String productPic;
        
            /** $column.columnComment */
                                        
                        private String productName;
        
            /** $column.columnComment */
                                        
                        private String productBrand;
        
            /** $column.columnComment */
                                        
                        private String productSn;
        
            /** 销售价格 */
                                        
                        private BigDecimal productPrice;
        
            /** 购买数量 */
                                        
                        private Long productQuantity;
        
            /** 商品sku编号 */
                                        
                        private String productSkuId;
        
            /** 商品sku条码 */
                                        
                        private String productSkuCode;
        
            /** 商品分类id */
                                        
                        private String productCategoryId;
        
            /** 商品促销名称 */
                                        
                        private String promotionName;
        
            /** 商品促销分解金额 */
                                        
                        private BigDecimal promotionAmount;
        
            /** 优惠券优惠分解金额 */
                                        
                        private BigDecimal couponAmount;
        
            /** 积分优惠分解金额 */
                                        
                        private BigDecimal integrationAmount;
        
            /** 该商品经过优惠后的分解金额 */
                                        
                        private BigDecimal realAmount;
        
            /** $column.columnComment */
                                        
                        private Long giftIntegration;
        
            /** $column.columnComment */
                                        
                        private Long giftGrowth;
        
            /** 商品销售属性:[{"key":"颜色","value":"颜色"},{"key":"容量","value":"4G"}] */
                                        
                        private String productAttr;
        
            /** 删除表示(0:No,1:Yes) */
                                        
                        
                                /** 租户编号 */
                                        
                        private String tenantId;
        
            }
