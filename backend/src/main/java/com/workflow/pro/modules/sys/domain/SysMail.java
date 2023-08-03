package com.workflow.pro.modules.sys.domain;

import lombok.Data;

/**
 * 邮箱模型
 *
 * Author: SOME
 * CreateTime: 2019/10/23
 * */
@Data
public class SysMail {

    /**
     * 编号
     * */
    private String id;

    /**
     * 标题
     * */
    private String subject;

    /**
     * 内容
     * */
    private String content;

    /**
     * 收件方
     * */
    private String to;

    /**
     * 收件方
     * */
    private String from;

}
