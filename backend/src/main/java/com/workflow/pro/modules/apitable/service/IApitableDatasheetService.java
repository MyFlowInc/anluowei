package com.workflow.pro.modules.apitable.service;

import java.io.IOException;
import java.util.List;


import cn.hutool.json.JSONArray;
import com.baomidou.mybatisplus.extension.service.IService;
import com.workflow.pro.common.web.base.page.PageResponse;
import com.workflow.pro.common.web.base.page.Pageable;


import com.workflow.pro.modules.apitable.exception.BusinessExceptionNew;
import com.workflow.pro.modules.apitable.param.ApitableDatasheetRequest;
import com.workflow.pro.modules.apitable.domain.ApitableDatasheet;

import com.apitable.client.api.model.CreateRecordRequest;

/**
 * 数据格IService接口
 *
 * @author some
 * @date 2023-05-22
 */
public interface IApitableDatasheetService extends IService<ApitableDatasheet> {
    /**
     * 查询数据格
     *
     * @param id 数据格ID
     * @return 数据格
     */
    ApitableDatasheet selectApitableDatasheetById(String id);

    ApitableDatasheet createDataSheet(ApitableDatasheet apitableDatasheet) throws IOException;

    /**
     * 查询数据格
     * @param  request pageSize pageNum
     * @return 数据格 分页集合
     * */
    PageResponse<ApitableDatasheet> page(ApitableDatasheetRequest request);

    /**
     * 查询数据格列表
     * @return 数据格集合
     */
    List<ApitableDatasheet> list(ApitableDatasheetRequest request);

    /**
     * 新增数据格
     *
     * @param apitableDatasheet 数据格
     * @return 结果
     */
    int insertApitableDatasheet(ApitableDatasheet apitableDatasheet);

    /**
     * 修改数据格
     *
     * @param apitableDatasheet 数据格
     * @return 结果
     */
    int updateApitableDatasheet(ApitableDatasheet apitableDatasheet);

    /**
     * 批量删除数据格
     *
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    int deleteApitableDatasheetByIds(String[] ids);

    /**
     * 删除数据格信息
     *
     * @param id 数据格ID
     * @return 结果
     */
    int deleteApitableDatasheetById(String id);


}
