// 表格列

import { AxiosResponse } from "axios";
import { apiCall, noLoginApiCall } from "../../network";
import _ from "lodash";

interface DSMetaQuery {
  dstId: string;
}

interface DstRecordItem {
  createBy: string;
  updateBy: string;
  createTime: string;
  updateTime: string;
  deleted: boolean;
  remark: null | unknown;
  id: string;
  recordId: string;
  dstId: string;
  data: string;
  revisionHistory: string;
  revision: number;
  fieldUpdatedInfo: string;
  sort: number;
  tenantId: null | unknown;
}
interface FetchRes {
  code: number;
  data: {
    record: DstRecordItem[];
    total: number;
  };
}
export function fetchDSRecord(data: DSMetaQuery): Promise<FetchRes> {
  return apiCall({
    url: "api/sys/apitableDatasheetRecord/page",
    method: "get",
    params: {
      ...data,
      pageNum: 1,
      pageSize: 999,
    },
  });
}
export const fetchRecords = async (dstId: string) => {
  const response = await fetchDSRecord({ dstId });
  const res = _.get(response, "data.record") || [];

  // generate table data
  const temp = res.map((item, index) => {
    try {
      const data = JSON.parse(item.data);
      return {
        ...data, // fieldId : value
        id: item.id + "",
        key: item.recordId + "",
        recordId: item.recordId + "",
        createDateTime: item.createTime,
      };
    } catch (error) {
      console.log("error--", error);
    }
  });
  return temp;
};

export interface AddDSCellsParams {
  dstId: string;
  fieldKey: "id";
  records: Array<{
    fields: {
      [key: string]: string | number | boolean | null | unknown;
    };
  }>;
}

export function addDSCells(data: AddDSCellsParams): Promise<any> {
  const { dstId, ...rest } = data;
  return apiCall({
    url: "api/sys/apitableDatasheetRecord/addFieldRecord",
    method: "post",
    params: {
      dstId,
    },
    data: rest,
  });
}

export interface UpdateDSCellsParams {
  dstId: string;
  fieldId?: string;
  fieldKey: "id";
  records: Array<{
    recordId: string;
    fields: {
      [fieldId: string]: string | number | boolean | null | unknown;
    };
  }>;
}

export function updateDSCells(data: UpdateDSCellsParams): Promise<any> {
  const { dstId, ...rest } = data;
  return apiCall({
    url: "api/sys/apitableDatasheetRecord/updateRecords",
    method: "post",
    params: {
      dstId,
    },
    data: rest,
  });
}

export interface ResetFieldTypeParams {
  dstId: string;
  fieldId: string | number | boolean | null | unknown;
}

export function resetFieldType(data: ResetFieldTypeParams): Promise<any> {
  const { dstId, fieldId } = data;
  return apiCall({
    url: "api/sys/apitableDatasheetRecord/resetFieldType",
    method: "post",
    params: {
      dstId,
      fieldId,
    },
  });
}

export interface DeleteDSCellsParams {
  dstId: string;
  recordIds: string[];
}

export function deleteDSCells(data: DeleteDSCellsParams): Promise<any> {
  const { dstId, recordIds } = data;
  return apiCall({
    url: "api/sys/apitableDatasheetRecord/delFieldData",
    method: "post",
    params: {
      dstId,
    },
    data: recordIds,
  });
}

// 根据 recordId 获取单条记录
export function fetchRecordById(data: any): Promise<any> {
  const { dstId, recordId } = data;
  return apiCall({
    url: "api/sys/apitableDatasheetRecord/page",
    method: "get",
    params: { dstId, recordId },
  });
}

// 无登录状态获取record

export function getRecord(data: any): Promise<any> {
  const { recordId } = data;
  return noLoginApiCall({
    url: "api/sys/apitableDatasheetRecord/getRecord",
    method: "get",
    params: { record_id: recordId },
  });
}

export function setRecordValue(data: any): Promise<any> {
  const { recordId, ...rest } = data;
  return noLoginApiCall({
    url: "api/sys/apitableDatasheetRecord/setRecordValue",
    method: "post",
    params: { record_id: recordId },
    data: rest,
  });
}
