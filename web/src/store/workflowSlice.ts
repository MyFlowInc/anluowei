import { createAsyncThunk, createSlice, PayloadAction } from '@reduxjs/toolkit'
import { RootState } from '.'
import _ from 'lodash'
import { DBApitableDatasheet } from '../interface/db_datasheet'
import { fetchDSMeta } from '../api/apitable/ds-meta'
import { fetchRecords } from '../api/apitable/ds-record'

export interface TableRow {
  key: string
  [key: string]: any
}

export interface FlowItemField {
  id: number
  fieldId: number
  flowItemId: number
  flowValue: string
}
export type WorkFlowInfo = DBApitableDatasheet

export interface WorkFlowStatusInfo {
  id: string
  name: string
  color: number
  type?: 'add' | 'edit' // 业务状态: 新增还是编辑
}

export type WorkFlowFieldInfo = TableColumnItem

export interface TableColumnItem {
  dstId: string // 助记属于哪个表
  fieldId: string
  statType: number
  type: number
  name: string
  dataIndex: string
  fieldConfig: any // 当前field的配置
}

export interface MetaData {
  fieldMap: {
    [propName: string]: any
  }
  view: Array<{
    columns: Array<{ fieldId: string }>
    rows: Array<{ recordId: string }>
  }>
}
export interface workflowState {
  curFlowDstId: string | undefined // 当前展示的dstId
  curShowMode: 'list' | 'status'
  WorkflowList: WorkFlowInfo[] //
  curFieldList: WorkFlowFieldInfo[] // 被替换
  curMetaData: MetaData | null // 当前的表格定义
  curTableStatusList: {
    id: string
    name: string
    color: number
  }[] // 当前的表格的状态
  curStatusFieldId: string // 当前的状态字段id
  curTableColumn: TableColumnItem[]
  curTableRows: any[] //  当前表格的行数据
  status: 'idle' | 'loading' | 'failed' // 接口状态
}
const initialState: workflowState = {
  curFlowDstId: undefined,
  curShowMode: 'list',
  WorkflowList: [],
  curFieldList: [],
  curMetaData: null,
  curTableStatusList: [],
  curStatusFieldId: '',
  curTableColumn: [], // 当前的列视图  可能切换
  curTableRows: [], //  当前表格的行数据
  status: 'idle',
}

// 刷新  meta data
export const freshCurMetaData = createAsyncThunk(
  'workflow/freshCurMetaData',
  async (dstId: string) => {
    const response = await fetchDSMeta({ dstId })
    const res = response.data.record
    res.map((item: any) => {
      item.metaData = JSON.parse(item.metaData)
    })
    const metaData = res[0].metaData
    let columns = _.get(metaData, 'views.0.columns')
    const fieldMap = _.get(metaData, 'fieldMap')
    if (!columns) {
      columns = []
    }
    columns = columns.map((item: any) => {
      const fieldId = item.fieldId
      const type = fieldMap[fieldId].type
      const name = fieldMap[fieldId].name
      return {
        dstId,
        fieldId: fieldId,
        dataIndex: fieldId,
        name: name,
        title: name,
        fieldConfig: fieldMap[fieldId],
        type,
      }
    })
    // The value we return becomes the `fulfilled` action payload
    return [metaData, columns]
  }
)

export const freshCurTableRows = createAsyncThunk(
  'workflow/freshCurTableRows',
  async (dstId: string) => {
    const rows = await fetchRecords(dstId)
    // The value we return becomes the `fulfilled` action payload
    return rows
  }
)
const initCurTableColumn = (
  state: workflowState,
  columns: TableColumnItem[]
) => {
  state.curTableColumn = columns
  const temp = _.find(columns, { type: 26 })
  if (temp) {
    state.curTableStatusList = _.get(temp, 'fieldConfig.property.options')
    state.curStatusFieldId = _.get(temp, 'fieldId')
  }
}

export const workflowSlice = createSlice({
  name: 'workflow',
  initialState,
  reducers: {
    updateCurFlowDstId: (state, action) => {
      state.curFlowDstId = action.payload
      // console.log('updateCurFlowDstId', state.curFlowDstId)
    },

    updateCurShowMode: (state, action) => {
      state.curShowMode = action.payload
      console.log('updateCurShowMode', state.curShowMode)
    },
    removeWorkflowList: (state, action: PayloadAction<string>) => {
       const list = [...state.WorkflowList]
        const id = action.payload
        const idx = _.findIndex(list, { id  })
        list.splice(idx, 1)
        state.WorkflowList = list

    },
    setWorkflowList: (state, action: PayloadAction<WorkFlowInfo[]>) => {
      const oldList = [...state.WorkflowList]
      const newList = action.payload
      newList.forEach((item) => {
        const idx = _.findIndex(oldList, { id: item.id })
        // 不存在 新增
        if (idx === -1) {
          oldList.push(item)
        }
        // 存在 则 替换
        if (idx > -1) {
          oldList.splice(idx, 1, item)
        }
      })

      state.WorkflowList = oldList
    },
    renameWorkflow: (state, action: PayloadAction<Partial<WorkFlowInfo>>) => {
      const { id, dstName } = action.payload
      const item = _.find(state.WorkflowList, { id })
      if (!item) return
      item.dstName = dstName || ''
      console.log('renameWorkflow', action)
    },

    setCurFieldList: (state, action) => {
      console.log('setCurFieldList', action)
      state.curFieldList = action.payload
    },
    setCurMetaData: (state, action) => {
      console.log('setCurMetaData', action)
      state.curMetaData = action.payload
    },
    updateFieldName: (state, action) => {
      console.log('updateFieldName', action)
      const { fieldId, name } = action.payload
    },
    setCurTableStatusList: (state, action) => {
      console.log('setCurTableStatusList', action)
      state.curTableStatusList = action.payload
    },
    // type=26 特殊列
    setCurTableColumn: (state, action) => {
      console.log('setCurTableColumn', action)
      const columns = action.payload

      initCurTableColumn(state, columns)
    },
    setCurTableRows: (state, action) => {
      console.log('setCurTableRows', action)
      state.curTableRows = action.payload
    },
    updateTableRow: (state, action) => {
      console.log('redux', action.payload)
      const {recordId , ...row} = action.payload
      const newData = [...state.curTableRows]
      const idx = _.find(newData, {recordId})
      const item= state.curTableRows[idx]
      newData.splice(idx, 1, {...item, ...row})
      state.curTableRows = newData
    }
  },
  // The `extraReducers` field lets the slice handle actions defined elsewhere,
  // including actions generated by createAsyncThunk or in other slices.
  extraReducers: (builder) => {
    builder.addCase(freshCurMetaData.fulfilled, (state, action) => {
      state.curMetaData = action.payload[0]
      initCurTableColumn(state, action.payload[1])
      // console.log('addCase---', action)
    })
    builder.addCase(freshCurTableRows.fulfilled, (state, action) => {
      state.curTableRows = action.payload
      // console.log('addCase---', action)
    })
  },
})

export const {
  updateCurFlowDstId,
  updateCurShowMode,
  removeWorkflowList,
  setWorkflowList,
  renameWorkflow,
  setCurFieldList,
  setCurMetaData,
  setCurTableColumn,
  setCurTableRows,
  updateTableRow,
  setCurTableStatusList,
} = workflowSlice.actions



const generateAttachedFlowList = (list: WorkFlowInfo[]) => {
  const ids = list.map((item) => item.createBy)
  const s = new Set(ids)
  const uniqueIds = Array.from(s)
  return uniqueIds.map((id) => {
    const children = list.filter((item) => item.createBy === id)
    return {
      id,
      nickname: _.get(children, '0.createUserInfo.nickname') + ' - 创建的表格',
      children,
    }
  }) as IAttachedWorkflowList[]
}
export interface IAttachedWorkflowList {
  id: string
  nickname: string
  children: any[]
}

export const selectCurFlowDstId = (state: RootState) =>
  state.workflow.curFlowDstId

export const selectCurShowMode = (state: RootState) =>
  state.workflow.curShowMode

export const selectCurFlowName = (state: RootState) => {
  const dstId = state.workflow.curFlowDstId
  const list = state.workflow.WorkflowList
  const item = _.find(list, { dstId })
  if (item) {
    return item.dstName
  }
  return ''
}
export const selectAllWorkflowList = (state: RootState) => {
  return state.workflow.WorkflowList
}

export const selectWorkflowList = (state: RootState) => {
  return state.workflow.WorkflowList.filter((item) => item.isCreator)
}

export const selectAttachedWorkflowList = (state: RootState) => {
  const res = state.workflow.WorkflowList.filter((item) => item.isDeveloper)
  return generateAttachedFlowList(res)
}

export const selectCurTableColumn = (state: RootState) =>
  state.workflow.curTableColumn

export const selectCurTableStatusList = (state: RootState) =>
  state.workflow.curTableStatusList

export const selectCurStatusFieldId = (state: RootState) =>
  state.workflow.curStatusFieldId

export const selectCurTableRows = (state: RootState) =>
  state.workflow.curTableRows

export const selectCurMetaData = (state: RootState) =>
  state.workflow.curMetaData

export default workflowSlice.reducer
