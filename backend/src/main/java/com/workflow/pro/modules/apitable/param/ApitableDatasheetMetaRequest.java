package com.workflow.pro.modules.apitable.param;

import com.workflow.pro.common.web.base.page.PageRequest;
import lombok.Data;
import lombok.EqualsAndHashCode;
import java.util.Date;
/**
 * 数据元数据Request参数请求接口
 *
 * @author some
 * @date 2023-05-22
 */

@Data
@EqualsAndHashCode(callSuper = true)
public class ApitableDatasheetMetaRequest  extends PageRequest  {

        /** Primary key */
            private String id;

            /** 表格id */
                                            private String dstId;

            /** 元数据 */
                                            private String metaData;

            /** 版本号 */
                                            private Long revision;

            /** 删除标识 */
                                            private Integer deleted;

            /** 排序 */
                                            private Long sort;

                                /** 租户编号 */
                                            private String tenantId;

    }