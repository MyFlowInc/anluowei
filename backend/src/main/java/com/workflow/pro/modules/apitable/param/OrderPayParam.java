package com.workflow.pro.modules.apitable.param;

import java.util.List;

import com.sun.jna.platform.unix.solaris.LibKstat.KstatNamed.UNION.STR;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 生成订单时传入的参数
 * Created by macro on 2018/8/30.
 */
@Data
@EqualsAndHashCode
public class OrderPayParam {
    @ApiModelProperty("支付方式")
    private Integer payType;


    @ApiModelProperty("订单id")
    private String orderSn;

}
