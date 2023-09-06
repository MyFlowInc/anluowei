import React, { useEffect, useState } from 'react'
import { FlowTableContainer } from './style'
import { Modal, Table, Space, Button, Tooltip } from 'antd'
import { ExclamationCircleFilled } from '@ant-design/icons'
import { useAppDispatch, useAppSelector } from '../../../store/hooks'
import {
  selectCopyModalProps,
  selectCurTableColumn,
  selectCurTableRows,
  selectMembers,
  setCopyModalPorps,
  TableColumnItem,
} from '../../../store/workflowSlice'
import _ from 'lodash'
import { ColumnsType } from 'antd/es/table'
import TableColumnRender from '../TableColumnRender'
import deleteSvg from '../assets/table/delete-bin.svg'
import editSvg from '../assets/table/edit.svg'
import CopyModal from '../../../BaseUI/CopyDialog'

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
  const tableData = useAppSelector(selectCurTableRows)
  const dstColumns = useAppSelector(selectCurTableColumn)
  const userList = useAppSelector(selectMembers)
  const copyModalProps = useAppSelector(selectCopyModalProps)
  const closeCopyModal = () => {
    dispatch(
      setCopyModalPorps({
        isShow: false,
        copyText: '',
      })
    )
  }

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

  useEffect(() => {
    const temp = dstColumns
      .map((item: TableColumnItem, cIndex: number) => {
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
    setTableColumn(columns as any)
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
      <CopyModal {...copyModalProps} closeModal={closeCopyModal} />
    </FlowTableContainer>
  )
}

export default FlowTable
