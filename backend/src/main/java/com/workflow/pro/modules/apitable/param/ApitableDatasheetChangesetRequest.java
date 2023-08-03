package com.workflow.pro.modules.apitable.param;

import com.workflow.pro.common.web.base.page.PageRequest;
import lombok.Data;
import lombok.EqualsAndHashCode;
import java.util.Date;
/**
 * 变更集收集Request参数请求接口
 *
 * @author some
 * @date 2023-05-22
 */

@Data
@EqualsAndHashCode(callSuper = true)
public class ApitableDatasheetChangesetRequest  extends PageRequest  {

        /** Primary key */
            private String id;

            /** 消息id */
                                            private String messageId;

            /** 表格id */
                                            private String dstId;

            /** 成员id */
                                            private Long memberId;

            /** 操作动作集合 */
                                            private String operations;

            /** 版本号 */
                                            private Long revision;

            /** 删除表示(0:No,1:Yes) */
                                            private Integer deleted;

            /** 排序 */
                                            private Long sort;

                                /** 租户编号 */
                                            private String tenantId;

    }