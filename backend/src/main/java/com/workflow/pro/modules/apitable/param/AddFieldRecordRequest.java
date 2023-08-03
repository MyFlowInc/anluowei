package com.workflow.pro.modules.apitable.param;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import com.apitable.client.api.model.RecordMap;

public class AddFieldRecordRequest {
    @JsonInclude(Include.NON_NULL)
    private List<RecordMap> records;

    public List<RecordMap> getRecords() {
        return this.records;
    }

    public void setRecords(List<RecordMap> records) {
        this.records = records;
    }

}
