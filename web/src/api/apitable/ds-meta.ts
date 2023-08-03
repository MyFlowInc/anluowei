// 表格列

import { apiCall } from '../../network'

interface DSMetaQuery {
  dstId: string
}

export function fetchDSMeta(data: DSMetaQuery) {
  return apiCall({
    url: 'api/sys/apitableDatasheetMeta/page',
    method: 'get',
    params: {
      ...data,
      pageNum: 1,
      pageSize: 999,
    },
  })
}

export interface UpdateDSMetaParams {
  dstId: string 
  fieldId?: string //填写这个标识更新 并校验此参数
  type?: string //传英文 的先 好标识
  name?: string
  property?: {
    options: any[]
  }
}

// 新增 or  更新
export function updateDSMeta(data: UpdateDSMetaParams) {
  const {dstId, ...rest} = data
  return apiCall({
    url: 'api/sys/apitableDatasheetMeta/addField',
    method: 'post',
    params: {
      spaceId:1,
      dstId
    },
    data:rest
  })
}

export type DeleteDSMetaParams = {
  dstId: string
  fieldIds: string[]
}
 

export function deleteDSMeta(data: DeleteDSMetaParams) {
  return apiCall({
    url: 'api/sys/apitableDatasheetMeta/delField',
    method: 'post',
    params: {
      dstId:data.dstId
    },
    data:data.fieldIds
  })
}
