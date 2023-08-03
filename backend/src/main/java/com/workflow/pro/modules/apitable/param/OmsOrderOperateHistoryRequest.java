package com.workflow.pro.modules.apitable.param;

import com.workflow.pro.common.web.base.page.PageRequest;
import lombok.Data;
import lombok.EqualsAndHashCode;
import java.util.Date;
/**
 * 订单操作历史记录Request参数请求接口
 *
 * @author some
 * @date 2023-06-09
 */

@Data
@EqualsAndHashCode(callSuper = true)
public class OmsOrderOperateHistoryRequest  extends PageRequest  {

        /** Primary Key */
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
                                            private Integer deleted;

                            /** 租户编号 */
                                            private String tenantId;

    }