package com.workflow.pro.modules.apitable.param;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode()

public class OrderVo {
    /** id */
    private String gradeId;
    /** （必填）商户订单号，应该由后台生成 */
    @ApiModelProperty(hidden = true)
    private String outTradeNo;


}
