package com.workflow.pro.modules.apitable.service.impl;

import cn.hutool.db.Page;
import cn.hutool.json.JSONArray;
import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.workflow.pro.common.context.UserContext;
import com.workflow.pro.common.web.base.page.PageResponse;
import com.workflow.pro.common.web.base.page.Pageable;


import com.workflow.pro.modules.apitable.domain.ApitableDatasheetMeta;
import com.workflow.pro.modules.apitable.domain.ApitableDeveloper;
import com.workflow.pro.modules.apitable.domain.dto.DataSheetRecordDTO;
import com.workflow.pro.modules.apitable.domain.ro.MetaMapRo;
import com.workflow.pro.modules.apitable.domain.ro.RecordMapRo;
import com.workflow.pro.modules.apitable.domain.ro.SimpleDatasheetMetaDTO;
import com.workflow.pro.modules.apitable.domain.ro.ViewMapRo;
import com.workflow.pro.modules.apitable.exception.BusinessExceptionNew;
import com.workflow.pro.modules.apitable.mapper.ApitableDatasheetMapper;
import com.workflow.pro.modules.apitable.domain.ApitableDatasheet;
import com.workflow.pro.modules.apitable.param.ApitableDatasheetRequest;
import com.workflow.pro.modules.apitable.param.ApitableDeveloperRequest;
import com.workflow.pro.modules.apitable.service.IApitableDatasheetService;
import com.workflow.pro.modules.apitable.service.IApitableDeveloperService;
import com.workflow.pro.modules.apitable.service.IDatasheetServiceImpl;
import com.workflow.pro.modules.apitable.utils.IdUtil;
import com.workflow.pro.modules.sys.domain.SysUser;
import com.workflow.pro.modules.sys.service.SysUserService;


import org.springframework.stereotype.Service;

import javax.annotation.Resource;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.UUID;

import com.apitable.client.api.model.CreateFieldRequest;

import com.apitable.client.api.model.CreateRecordRequest;
import com.apitable.client.api.model.RecordMap;
import com.apitable.client.api.model.builder.CreateFieldRequestBuilder;
import com.apitable.client.api.model.field.FieldTypeEnum;
import com.apitable.client.api.model.field.property.EmptyProperty;
import com.apitable.client.api.model.field.property.SingleTextFieldProperty;


/**
 * 数据格ServiceImpl业务层处理
 *
 * @author some
 * @date 2023-05-22
 */
@Service
public class ApitableDatasheetServiceImpl extends ServiceImpl<ApitableDatasheetMapper, ApitableDatasheet> implements IApitableDatasheetService {

    @Resource
    ApitableDatasheetMapper apitableDatasheetMapper;


    @Resource
    private SysUserService sysUserService;

    @Resource
    private IApitableDeveloperService developerService;

    /**
     * 用 户 服 务
     */
    @Resource
    private UserContext userContext;


    /**
     * 查询数据格
     *
     * @param id 数据格ID
     * @return 数据格
     */
    @Override
    public ApitableDatasheet selectApitableDatasheetById(String id) {
        return apitableDatasheetMapper.selectApitableDatasheetById(id);
    }

    @Override
    public ApitableDatasheet createDataSheet(ApitableDatasheet apitableDatasheet) throws IOException {
        if (apitableDatasheet.getId() != null) {
            apitableDatasheetMapper.updateApitableDatasheet(apitableDatasheet);
            return apitableDatasheet;
        }
        String randomString = UUID.randomUUID().toString().replaceAll("-", "").substring(0, 16);
        apitableDatasheet.setDstId(randomString);
        apitableDatasheet.setNodeId(randomString);
        save(apitableDatasheet);

        //build 具体数据到 meta表和 record表
        //创建单个文本字段示例：
        SingleTextFieldProperty singleTextFieldProperty = new SingleTextFieldProperty();
        singleTextFieldProperty.setDefaultValue("defaultValue");
        CreateFieldRequest<SingleTextFieldProperty> createFieldRequest = CreateFieldRequestBuilder
                .create()
                .ofType(FieldTypeEnum.SingleText)
                .withName("singleText")
                .withProperty(singleTextFieldProperty)
                .build();

        CreateFieldRequest<EmptyProperty> DateTime = CreateFieldRequestBuilder
                .create()
                .ofType(FieldTypeEnum.DateTime)
                .withName("DateTime")
                .withoutProperty()
                .build();

        System.out.print(createFieldRequest);
        System.out.print(createFieldRequest);

        return apitableDatasheet;
    }

    /**
     * 查询数据格列表
     * @return 数据格
     */
    @Override
    public List<ApitableDatasheet> list(ApitableDatasheetRequest request) {
        return apitableDatasheetMapper.selectApitableDatasheetList(request);
    }

    /**
     * 查询数据格
     * @return 数据格 分页集合
     * */
    @Override
    public PageResponse<ApitableDatasheet> page(ApitableDatasheetRequest request) {

        if (!Objects.equals(userContext.getUsername(), "admin")) {
            request.setCreateBy(userContext.getUserId());
        }
        List<String> ids = new ArrayList<>();
        if (request.getIsDeveloper() != null && request.getIsDeveloper() == 1) {
            ApitableDeveloperRequest apitableDeveloperRequest = new ApitableDeveloperRequest();
            apitableDeveloperRequest.setUserId(userContext.getUserId());
            apitableDeveloperRequest.setPageNum(request.getPageNum());
            apitableDeveloperRequest.setPageSize(request.getPageSize());
            List<ApitableDeveloper> list1 = developerService.list(apitableDeveloperRequest);

            if (list1.size() > 0) {
                for (ApitableDeveloper developer : list1) {
                    ids.add(developer.getDstId());
                }
            }
            if (ids.size() > 0) {
                //就不查询 创建者相关
                request.setCreateBy(null);
                request.setIds(ids);
            }
            else {
                PageResponse<ApitableDatasheet> a = new PageResponse<>();
                a.setRecord(new ArrayList<>());
                a.setTotal(0);
                return a;
            }
        }
        //1.如果传isDeveloper 就认为 只筛选我协作的
        //2.不传 代表所有的 我创建和我协作的
        //3.传了 先筛选 有无协作 有就id 遍历，无id 就是说 id >0 或协作者=1 都要制空查询条件

        //获取我协作的列表
        PageResponse<ApitableDatasheet> page = Pageable.of(request, (() -> apitableDatasheetMapper.selectApitableDatasheetList(request)));
        List<ApitableDatasheet> list = new ArrayList<>();
        for (ApitableDatasheet datasheet : page.getRecord()) {
            //如果此人在 协作表里就是协作者 如果不是就是创建者
            ApitableDeveloper one = developerService.getOne(new QueryWrapper<ApitableDeveloper>().eq("dst_id", datasheet.getDstId()).eq("user_id", userContext.getUserId()), false);
            //为创建者 如果 不在协作 且不筛选协作
            if (one == null) {
                datasheet.setIsCreator(true);
                one = new ApitableDeveloper();
                one.setAllowEdit(1);
                one.setAllowManage(1);
                one.setAllowWatch(1);
                one.setAllowSave(1);
                one.setDstId(datasheet.getDstId());
            }
            else {
                datasheet.setIsDeveloper(true);
            }

            SysUser byId = sysUserService.getById(datasheet.getCreateBy());
            datasheet.setCreateUserInfo(byId);
            datasheet.setShareSetting(one);
            list.add(datasheet);
        }
        page.setRecord(list);
        return page;
    }

    /**
     * 新增数据格
     *
     * @param apitableDatasheet 数据格
     * @return 结果
     */

    @Override
    public int insertApitableDatasheet(ApitableDatasheet apitableDatasheet) {
        return apitableDatasheetMapper.insertApitableDatasheet(apitableDatasheet);
    }

    /**
     * 修改数据格
     *
     * @param apitableDatasheet 数据格
     * @return 结果
     */
    @Override
    public int updateApitableDatasheet(ApitableDatasheet apitableDatasheet) {
        return apitableDatasheetMapper.updateApitableDatasheet(apitableDatasheet);
    }

    /**
     * 删除数据格对象
     *
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    @Override
    public int deleteApitableDatasheetByIds(String[] ids) {
        return apitableDatasheetMapper.deleteApitableDatasheetByIds(ids);
    }

    /**
     * 删除数据格信息
     *
     * @param id 数据格ID
     * @return 结果
     */
    @Override
    public int deleteApitableDatasheetById(String id) {
        return apitableDatasheetMapper.deleteApitableDatasheetById(id);
    }

}
