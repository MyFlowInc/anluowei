package com.workflow.pro.modules.apitable.param;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
public class AddFieldRecordUpdate {
    /** 订单id */
    private String filedKey;

    /** 订单编号 */
    private String filedValue;
}
