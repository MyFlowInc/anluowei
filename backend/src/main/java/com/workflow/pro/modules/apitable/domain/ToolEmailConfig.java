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
 * 邮箱配置对象 tool_email_config
 *
 * @author some
 * @date 2023-05-24
 */


@Alias("ToolEmailConfig")
@TableName("tool_email_config")
@Data
public class ToolEmailConfig {

    private static final long serialVersionUID = 1L;
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
