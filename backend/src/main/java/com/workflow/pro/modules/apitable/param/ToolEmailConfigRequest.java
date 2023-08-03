package com.workflow.pro.modules.apitable.param;

import com.workflow.pro.common.web.base.page.PageRequest;
import lombok.Data;
import lombok.EqualsAndHashCode;
import java.util.Date;
/**
 * 邮箱配置Request参数请求接口
 *
 * @author some
 * @date 2023-05-24
 */

@Data
@EqualsAndHashCode(callSuper = true)
public class ToolEmailConfigRequest  extends PageRequest  {

        /** ID */
            private Long configId;

            /** 收件人 */
                                            private String fromUser;

            /** 邮件服务器SMTP地址 */
                                            private String host;

            /** 密码 */
                                            private String pass;

            /** 端口 */
                                            private String port;

            /** 发件者用户名 */
                                            private String user;

    }