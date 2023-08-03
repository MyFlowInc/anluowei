package com.workflow.pro.modules.apitable.param;

import com.workflow.pro.common.web.base.page.PageRequest;
import lombok.Data;
import lombok.EqualsAndHashCode;
import java.util.Date;
/**
 * 支付宝配置类Request参数请求接口
 *
 * @author some
 * @date 2023-05-24
 */

@Data
@EqualsAndHashCode(callSuper = true)
public class ToolAlipayConfigRequest  extends PageRequest  {

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