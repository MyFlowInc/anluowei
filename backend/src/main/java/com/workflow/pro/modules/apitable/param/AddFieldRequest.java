package com.workflow.pro.modules.apitable.param;

import cn.hutool.json.JSONObject;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import com.apitable.client.api.model.CreateFieldRequest;
import com.apitable.client.api.model.field.property.BaseFieldProperty;


public class AddFieldRequest {



    private String type;


    private String fieldId;
    private String name;
    private JSONObject property;

    public String getFieldId() {
        return fieldId;
    }

    public void setFieldId(String fieldId) {
        this.fieldId = fieldId;
    }


    public String getType() {
        return this.type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public JSONObject getProperty() {
        return this.property;
    }

    public void setProperty(JSONObject property) {
        this.property = property;
    }

    public String toString() {
        return "CreateFieldRequest{type='" + this.type + '\'' + ", name='" + this.name + '\'' + ", property=" + this.property + '}';
    }

    private  CreateFieldRequest<? extends BaseFieldProperty> field;

}
