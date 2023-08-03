package com.workflow.pro.modules.apitable.domain;


import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import com.workflow.pro.common.web.base.domain.BaseDomain;
import com.baomidou.mybatisplus.annotation.TableName;
import org.apache.ibatis.type.Alias;
import lombok.EqualsAndHashCode;

import java.util.Date;

/**
 * 支付宝配置类对象 tool_alipay_config
 *
 * @author some
 * @date 2023-05-24
 */


@Alias("ToolAlipayConfig")
@TableName("tool_alipay_config")
@Data
public class ToolAlipayConfig {

    private static final long serialVersionUID = 1L;
    /** ID */
    private Long configId;

    /** 应用ID */
    private String appId;

    /** 编码 */
    private String charset;

    /** 类型 固定格式json */
    private String format;

    /** 网关地址 */
    private String gatewayUrl;

    /** 异步回调 */
    private String notifyUrl;

    /** 私钥 */
    private String privateKey;

    /** 公钥 */
    private String publicKey;

    /** 回调地址 */
    private String returnUrl;

    /** 签名方式 */
    private String signType;

    /** 商户号 */
    private String sysServiceProviderId;
}
