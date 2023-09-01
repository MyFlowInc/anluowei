import React, { useEffect, useState } from 'react'
import _ from 'lodash'
import { Button, Form } from 'antd'
import styled from 'styled-components'
import { StatusTag } from './StatusTag'
import { useAppDispatch, useAppSelector } from '../../../store/hooks'
import {
  freshCurMetaData,
  freshCurTableRows,
  selectCurFlowDstId,
  selectCurTableRows,
  WorkFlowFieldInfo,
  WorkFlowStatusInfo,
} from '../../../store/workflowSlice'
import TableRecordForm from './TableRecordForm'
import { PlusOutlined } from '@ant-design/icons'
import { NoFieldData } from './NoFieldData'
import { FlowItemTableDataType } from '../FlowTable/core'
import {
  addDSCells,
  AddDSCellsParams,
  updateDSCells,
  UpdateDSCellsParams,
} from '../../../api/apitable/ds-record'
import { UpdateDSMetaParams, updateDSMeta } from '../../../api/apitable/ds-meta'
import { selectUser } from '../../../store/globalSlice'
import { SocketMsgType, sendWebSocketMsg } from '../../../api/apitable/ws-msg'

const CustomModalRoot = styled.div`
  width: 100%;
  min-height: 180px;
  display: flex;
  flex-direction: column;
  height: auto;
  position: relative;
  background-color: #ffffff;
  background-clip: padding-box;
  border: 0;
  border-radius: 8px;
  box-shadow: 0 6px 16px 0 rgb(0 0 0 / 8%), 0 3px 6px -4px rgb(0 0 0 / 12%),
    0 9px 28px 8px rgb(0 0 0 / 5%);
  pointer-events: auto;
  padding: 20px 24px;
  .header {
    display: flex;
    width: 100%;
    justify-content: space-between;
    .title {
      margin: 0;
      color: rgba(0, 0, 0, 0.88);
      font-weight: 600;
      font-size: 16px;
      line-height: 1.5;
      word-wrap: break-word;
    }
    .status {
    }
  }

  .content {
    flex: 1;
    .form-list {
      margin-bottom: 16px;
    }
    .add-icon {
      display: flex;
      align-items: center;
      padding-right: 12px;
      background-color: #ffffff;
      border: 1px solid #d9d9d9;
      border-style: dashed;
      border-radius: 4px;
      :hover {
        border-color: #4096ff;
        color: #4096ff;
        cursor: pointer;
      }
      width: fit-content;
      .icon {
        width: 18px;
      }
      .text {
        margin-left: 12px;
      }
    }
  }
  .footer {
    margin-top: 12px;
    .cancel {
      width: 45%;
      color: #605bff;
    }
    .submit {
      margin-left: 10%;
      width: 45%;
      background: #605bff;
    }
  }
`
interface CustomModalProps {
  title: string
  open: boolean
  setOpen: (value: boolean) => void
  freshFlowItem: () => void
  statusList: WorkFlowStatusInfo[]
  dstColumns: WorkFlowFieldInfo[]
  modalType: string
  setModalType: (v: string) => void
  editFlowItemRecord: FlowItemTableDataType | undefined
}

const CustomModal: React.FC<CustomModalProps> = (props) => {
  const {
    title,
    open,
    setOpen,
    statusList,
    dstColumns,
    modalType,
    setModalType,
    editFlowItemRecord,
  } = props
  const [form, setForm] = useState<{ [id: string]: string }>({})
  const [inputForm] = Form.useForm()
  const user = useAppSelector(selectUser)
  const curDstId = useAppSelector(selectCurFlowDstId)
  const curTableRows = useAppSelector(selectCurTableRows)
  const dispatch = useAppDispatch()

  // esc handler
  useEffect(() => {
    if (!open) {
      return
    }
    const keydownHandler = (e: KeyboardEvent) => {
      if (e.code === 'Escape') {
        setOpen(false)
      }
    }
    document.addEventListener('keydown', keydownHandler, true)
    return () => {
      document.removeEventListener('keydown', keydownHandler, true)
    }
  }, [open])

  useEffect(() => {
    if (!open) {
      return
    }
    inputForm.resetFields()
    if (modalType === 'edit' && editFlowItemRecord) {
      const { key, flowItemId, statusId, ...temp } = editFlowItemRecord
      setForm(temp)
    }
    if (modalType === 'add') {
      if (statusList && statusList.length > 0) {
      }
      setForm({})
    }
  }, [open])

  const addFieldHandler = async () => {
    if (!curDstId) {
      return
    }
    const param: UpdateDSMetaParams = {
      dstId: curDstId,
      name: '字段' + (dstColumns.length + 1),
      type: 'SingleText',
    }
    try {
      await updateDSMeta(param)
      await dispatch(freshCurMetaData(curDstId))
    } catch (error) {
      console.log('updateFieldHandler error', error)
    }
  }

  const addTableRecordHandler = async () => {
    console.log('addTableRecordHandler', form)
    inputForm.setFieldsValue(form)
    const params: AddDSCellsParams = {
      dstId: curDstId!,
      fieldKey: 'id',
      records: [
        {
          fields: form,
        },
      ],
    }
    try {
      await inputForm.validateFields()
      await addDSCells(params)
      dispatch(freshCurTableRows(curDstId!))

      // 同步 ws
      sendWebSocketMsg({
        user,
        dstId: curDstId!,
        type: SocketMsgType.AddRecords,
        recordId: '',
        row: {},
      })
      setOpen(false)
    } catch (error) {
      console.log(error)
    }
  }

  const editFormItemHandler = async () => {
    console.log('editFormItemHandler', form)
    const { recordId, id, ...rest } = form
    inputForm.setFieldsValue(rest)

    // 特殊需求 重置邀请状态
    const res = _.find(dstColumns, { name_en: 'interview_date' }) as any
    if (res) {
      const interview_date_fieldId = res.fieldId
      const newValue = rest[interview_date_fieldId]
      const oldValue = _.find(curTableRows, { recordId })?.[
        interview_date_fieldId
      ]
      if (newValue && oldValue && newValue !== oldValue) {
        const res2 = _.find(dstColumns, { name_en: 'invite_status' }) as any
        const invite_status_fieldId = res2.fieldId
        if (rest[invite_status_fieldId]) {
          rest[invite_status_fieldId] = '未邀请'
        }
      }
    }

    const params: UpdateDSCellsParams = {
      dstId: curDstId!,
      fieldKey: 'id',
      records: [
        {
          recordId,
          fields: rest,
        },
      ],
    }
    try {
      await inputForm.validateFields()
      await updateDSCells(params)
      dispatch(freshCurTableRows(curDstId!))
      // 同步 ws
      sendWebSocketMsg({
        user,
        dstId: curDstId!,
        type: SocketMsgType.SetRecords,
        recordId,
        row: rest,
      })
      setOpen(false)
    } catch (error) {
      console.log(error)
    }
  }

  return (
    <CustomModalRoot>
      <div className="header">
        <div className="title">{title}</div>
        <div className="status">
          <StatusTag statusList={statusList} {...{ form, setForm }} />
        </div>
      </div>
      <div className="content">
        <div className="form-list">
          <Form
            form={inputForm}
            name="recordForm"
            colon={false}
            labelCol={{ span: 6 }}
          >
            {dstColumns.length > 0 ? (
              <TableRecordForm
                form={form}
                setForm={setForm}
                dstColumns={dstColumns}
                record={editFlowItemRecord!}
                modalType={modalType}
              />
            ) : (
              <NoFieldData />
            )}
          </Form>
        </div>
        <div
          className="add-icon"
          onClick={() => {
            addFieldHandler()
          }}
        >
          <PlusOutlined className="icon" />
          <div className="text">添加</div>
        </div>
      </div>
      <div className="footer">
        <Button
          className="cancel"
          onClick={() => {
            setOpen(false)
            setModalType('add')
          }}
        >
          取消
        </Button>
        {modalType === 'add' && (
          <Button
            className="submit"
            type="primary"
            onClick={() => {
              addTableRecordHandler()
            }}
          >
            创建
          </Button>
        )}
        {modalType === 'edit' && (
          <Button
            className="submit"
            type="primary"
            onClick={() => {
              editFormItemHandler()
            }}
          >
            修改
          </Button>
        )}
      </div>
    </CustomModalRoot>
  )
}

export default CustomModal
