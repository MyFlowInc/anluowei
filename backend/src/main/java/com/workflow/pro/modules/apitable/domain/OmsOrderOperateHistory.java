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
 * 订单操作历史记录对象 oms_order_operate_history
 *
 * @author some
 * @date 2023-06-09
 */


@Alias("OmsOrderOperateHistory")
@TableName("oms_order_operate_history")
@EqualsAndHashCode(callSuper = true)
@Data
public class OmsOrderOperateHistory extends BaseDomain    {
private static final long serialVersionUID=1L;

        /** Primary Key */
        
                /**  @TableId(value = "id", type = IdType.AUTO)*/
        @TableField(value = "id")
                        private String id;
        
            /** 订单id */
                                        
                        private String orderId;
        
            /** 操作人：用户；系统；后台管理员 */
                                        
                        private String operateMan;
        
                /** 订单状态：0->待付款；1->待发货；2->已发货；3->已完成；4->已关闭；5->无效订单 */
                                        
                        private Integer orderStatus;
        
            /** 备注 */
                                        
                        private String note;
        
            /** 删除表示(0:No,1:Yes) */
                                        
                        
                            /** 租户编号 */
                                        
                        private String tenantId;
        
            }
