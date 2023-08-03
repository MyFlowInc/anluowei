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
import java.util.Date;

/**
 * 订单退货申请对象 oms_order_return_apply
 *
 * @author some
 * @date 2023-06-09
 */


@Alias("OmsOrderReturnApply")
@TableName("oms_order_return_apply")
@EqualsAndHashCode(callSuper = true)
@Data
public class OmsOrderReturnApply extends BaseDomain    {
private static final long serialVersionUID=1L;

        /** Primary Key */
        
                /**  @TableId(value = "id", type = IdType.AUTO)*/
        @TableField(value = "id")
                        private String id;
        
            /** 订单id */
                                        
                        private String orderId;
        
            /** 收货地址表id */
                                        
                        private String companyAddressId;
        
            /** 退货商品id */
                                        
                        private String productId;
        
            /** 订单编号 */
                                        
                        private String orderSn;
        
                /** 会员用户名 */
                                        
                        private String memberUsername;
        
            /** 退款金额 */
                                        
                        private BigDecimal returnAmount;
        
            /** 退货人姓名 */
                                        
                        private String returnName;
        
            /** 退货人电话 */
                                        
                        private String returnPhone;
        
            /** 申请状态：0->待处理；1->退货中；2->已完成；3->已拒绝 */
                                        
                        private Integer status;
        
            /** 处理时间 */
                                        
                        private Date handleTime;
        
            /** 商品图片 */
                                        
                        private String productPic;
        
            /** 商品名称 */
                                        
                        private String productName;
        
            /** 商品品牌 */
                                        
                        private String productBrand;
        
            /** 商品销售属性：颜色：红色；尺码：xl; */
                                        
                        private String productAttr;
        
            /** 退货数量 */
                                        
                        private Long productCount;
        
            /** 商品单价 */
                                        
                        private BigDecimal productPrice;
        
            /** 商品实际支付单价 */
                                        
                        private BigDecimal productRealPrice;
        
            /** 原因 */
                                        
                        private String reason;
        
            /** 描述 */
                                        
                        private String description;
        
            /** 凭证图片，以逗号隔开 */
                                        
                        private String proofPics;
        
            /** 处理备注 */
                                        
                        private String handleNote;
        
            /** 处理人员 */
                                        
                        private String handleMan;
        
            /** 收货人 */
                                        
                        private String receiveMan;
        
            /** 收货时间 */
                                        
                        private Date receiveTime;
        
            /** 收货备注 */
                                        
                        private String receiveNote;
        
            /** 删除表示(0:No,1:Yes) */
                                        
                        
                            /** 租户编号 */
                                        
                        private String tenantId;
        
            }
