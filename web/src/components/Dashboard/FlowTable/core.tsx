import React, { useEffect, useState } from 'react'
import { FlowTableContainer } from './style'
import { Modal, Table, Space, Button, Tooltip } from 'antd'
import { ExclamationCircleFilled, LinkOutlined } from '@ant-design/icons'
import { useAppDispatch, useAppSelector } from '../../../store/hooks'
import {
  selectCurTableColumn,
  selectCurTableRows,
  selectMembers,
  selectCurFlowDstId,
  freshCurTableRows,
} from '../../../store/workflowSlice'
import _ from 'lodash'
import { ColumnsType } from 'antd/es/table'
import TableColumnRender from '../TableColumnRender'
import deleteSvg from '../assets/table/delete-bin.svg'
import editSvg from '../assets/table/edit.svg'
import { clipboardWriteText } from '../../../util/clipboard'
import {
  UpdateDSCellsParams,
  updateDSCells,
} from '../../../api/apitable/ds-record'
import { SocketMsgType, sendWebSocketMsg } from '../../../api/apitable/ws-msg'
import { selectUser } from '../../../store/globalSlice'
export interface FlowItemTableDataType {
  key: string
  flowItemId: number
  statusId: string
  [propName: string]: any
}

// rowSelection object indicates the need for row selection
const rowSelection = {
  onChange: (
    selectedRowKeys: React.Key[],
    selectedRows: FlowItemTableDataType[]
  ) => {
    console.log(
      `selectedRowKeys: ${selectedRowKeys}`,
      'selectedRows: ',
      selectedRows
    )
  },
  getCheckboxProps: (record: FlowItemTableDataType) => ({
    disabled: record.name === 'Disabled User', // Column configuration not to be checked
    name: record.name,
  }),
}

interface FlowTableProps {
  className?: string
  showMode?: 'list' | 'status'
  title?: string
  dstId: string
  statusId?: string
  statusFieldId?: string
  setEditFlowItemRecord: (v: FlowItemTableDataType) => void
  deleteFlowItem: (recordId: string) => void
  modalType: string
  setModalType?: (v: string) => void
  setOpen?: (v: boolean) => void
  reader: boolean
  writer: boolean
  manager: boolean
}

export const FlowTable: React.FC<Partial<FlowTableProps>> = (props) => {
  const {
    className,
    deleteFlowItem,
    modalType,
    setModalType,
    setOpen,
    setEditFlowItemRecord,
    statusId,
    statusFieldId,
    reader,
    writer,
    manager,
  } = props
  const { confirm } = Modal
  const dispatch = useAppDispatch()
  const user = useAppSelector(selectUser)

  const curDstId = useAppSelector(selectCurFlowDstId)
  const tableData = useAppSelector(selectCurTableRows)
  const dstColumns = useAppSelector(selectCurTableColumn)
  const userList = useAppSelector(selectMembers)
  const [tableColumn, setTableColumn] = useState<
    ColumnsType<FlowItemTableDataType>
  >([])

  const delHandle = async (
    text: string,
    record: FlowItemTableDataType,
    index: number
  ) => {
    console.log('delHandle', record)
    confirm({
      title: '是否确认删除?',
      icon: <ExclamationCircleFilled />,
      okText: '确认',
      okType: 'danger',
      cancelText: '取消',
      onOk() {
        console.log(222, record)
        // TODO change name
        deleteFlowItem?.(record.recordId)
        // 同步room server
      },
      onCancel() {
        console.log('Cancel')
      },
    })
  }
  const editHandle = async (
    text: string,
    record: FlowItemTableDataType,
    index: number
  ) => {
    console.log('editHandle', record)
    setEditFlowItemRecord?.(record)
    setModalType?.('edit')
    setOpen?.(true)
  }
  const copyInviteLink = async (record: FlowItemTableDataType) => {
    try {
      console.log('copyInviteLink', record)
      const invite_item = _.find(dstColumns, {
        name_en: 'invite_status',
      }) as any

      if (!invite_item) return
      const inviteFieldId = invite_item.fieldId

      const name_item = _.find(dstColumns, {
        name_en: 'interviewer_name',
      }) as any
      if (!name_item) return
      const nameFieldId = name_item.fieldId

      const path =
        window.location.origin +
        '/invite?recordId=' +
        record.recordId +
        '&&inviteFieldId=' +
        inviteFieldId +
        '&&nameFieldId=' +
        nameFieldId
      clipboardWriteText(path)

      const inviteStatus = record[inviteFieldId]

      if (inviteStatus === '未邀请' || !inviteStatus) {
        // 修改状态
        // TODO 这个接口有没有必要用rest, 业务意图只想更新一个字段
        const { id, recordId, key, ...rest } = record
        const params: UpdateDSCellsParams = {
          dstId: curDstId!,
          fieldKey: 'id',
          records: [
            {
              recordId: record.recordId,
              fields: {
                ...rest,
                [inviteFieldId]: '未接受',
              },
            },
          ],
        }
        await updateDSCells(params)
        dispatch(freshCurTableRows(curDstId!))
        // 同步
        sendWebSocketMsg({
          user,
          dstId: curDstId!,
          type: SocketMsgType.SetRecords,
          recordId: record.recordId,
          row: {
            [inviteFieldId]: '未接受',
          },
        })
      }
    } catch (e) {
      console.log(e)
    }
  }
  useEffect(() => {
    const temp = dstColumns
      .map((item: any, cIndex: any) => {
        return {
          ...item,
          ellipsis: true,
          onCell: (record: any, rIndex: number) => ({
            rIndex,
            cIndex,
            record,
            column: item,
            reader,
            writer,
            manager,
            userList,
          }),
        }
      })
      .filter((item: any) => {
        if (statusFieldId) {
          return item.name_en !== 'interview_status'
        } else {
          return true
        }
      })

    const action =
      (writer && {
        title: '操作',
        dataIndex: 'actions',
        render: (
          text: string,
          record: FlowItemTableDataType,
          index: number
        ) => {
          return (
            <Space>
              <Tooltip placement="top" title={'编辑'}>
                <Button
                  type="text"
                  icon={<img src={editSvg} />}
                  onClick={() => {
                    editHandle(text, record, index)
                  }}
                />
              </Tooltip>
              {manager && (
                <Tooltip placement="top" title={'删除'}>
                  <Button
                    type="text"
                    icon={<img src={deleteSvg} />}
                    onClick={() => {
                      delHandle(text, record, index)
                    }}
                  />
                </Tooltip>
              )}
              {/* <Tooltip placement="top" title={'复制邀请链接'}>
                <Button
                  type="text"
                  icon={<LinkOutlined />}
                  onClick={() => {
                    copyInviteLink(record)
                  }}
                />
              </Tooltip> */}
            </Space>
          )
        },
      }) ||
      {}
    const columns = [...temp, action]
    columns.forEach((item: any, index: number) => {
      switch (index) {
        case 0:
          item.fixed = 'left'
          return
        case columns.length - 1:
          item.fixed = 'right'
          item.align = 'center'
          return
        default:
          item.width = 200
      }
    })
    setTableColumn(columns)
  }, [dstColumns, reader, writer, manager, userList])

  const filterTableData = (records: any[]) => {
    if (!statusId) {
      return records
    }
    if (!statusFieldId) {
      return []
    }
    return records.filter((item) => {
      return item[statusFieldId] === statusId
    })
  }
  return (
    <FlowTableContainer className={'card-table-container' + ' ' + className}>
      <Table
        size="small"
        components={{
          body: {
            cell: TableColumnRender,
          },
        }}
        rowSelection={
          tableData.length > 0
            ? {
                type: 'checkbox',
                ...rowSelection,
              }
            : undefined
        }
        pagination={false}
        columns={tableColumn}
        scroll={{ x: true }}
        dataSource={filterTableData(tableData)}
      />
    </FlowTableContainer>
  )
}

export default FlowTable
