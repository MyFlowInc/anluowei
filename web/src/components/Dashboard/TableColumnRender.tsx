import React from 'react'
import { Button, Tag, Avatar } from 'antd'
import { Link } from 'react-router-dom'
import { DiscussModal } from './FormModal/TypeEditor/TypeDiscuss'
import _ from 'lodash'
import { flatList, freshCurTableRows } from '../../store/workflowSlice'
import { CopyOutlined, SendOutlined } from '@ant-design/icons'
import { clipboardWriteText } from '../../util/clipboard'
import { FlowItemTableDataType } from './FlowTable/core'
import {
  UpdateDSCellsParams,
  updateDSCells,
} from '../../api/apitable/ds-record'
import store from '../../store'
import { SocketMsgType, sendWebSocketMsg } from '../../api/apitable/ws-msg'
export const NumFieldType = {
  NotSupport: 0,
  Text: 1, // Multi-line text
  Number: 2,
  SingleSelect: 3,
  MultiSelect: 4,
  DateTime: 5,
  Attachment: 6,
  Link: 7,
  URL: 8,
  Email: 9,
  Phone: 10,
  Checkbox: 11,
  Rating: 12,
  Member: 13,
  LookUp: 14,
  // RollUp : 15,
  Formula: 16,
  Currency: 17,
  Percent: 18,
  SingleText: 19, //
  AutoNumber: 20,
  CreatedTime: 21,
  LastModifiedTime: 22,
  CreatedBy: 23,
  LastModifiedBy: 24,
  Cascader: 25,
  OptionStatus: 26, // 状态
  discuss: 27,
  InterviewStatus: 30,
  InviteStatus: 31,
  Interviewer: 32, // 面试官
  DeniedField: 999, // no permission column
}
export const ReverSedNumFieldType = {
  '0': 'NotSupport',
  '1': 'Text',
  '2': 'Number',
  '3': 'SingleSelect',
  '4': 'MultiSelect',
  '5': 'DateTime',
  '6': 'Attachment',
  '7': 'Link',
  '8': 'URL',
  '9': 'Email',
  '10': 'Phone',
  '11': 'Checkbox',
  '12': 'Rating',
  '13': 'Member',
  '14': 'LookUp',
  '16': 'Formula',
  '17': 'Currency',
  '18': 'Percent',
  '19': 'SingleText',
  '20': 'AutoNumber',
  '21': 'CreatedTime',
  '22': 'LastModifiedTime',
  '23': 'CreatedBy',
  '24': 'LastModifiedBy',
  '25': 'Cascader',
  '26': 'OptionStatus',
  '27': 'discuss',
  '30': 'InterviewStatus',
  '31': 'InviteStatus',
  '32': 'Interviewer',
  '999': 'DeniedField',
}

interface TableColumnRenderProps {
  rIndex: number
  cIndex: number
  record: any
  column: any
  reader: boolean
  writer: boolean
  manager: boolean
  searchText: string
  userList: any
  children: React.ReactNode
}

const TableColumnRender: React.FC<TableColumnRenderProps> = ({
  rIndex,
  cIndex,
  record,
  column,
  reader,
  writer,
  manager,
  searchText,
  userList,
  children,
  ...restProps
}) => {
  if (column === undefined || record === undefined) {
    return <td {...restProps}>{children}</td>
  }

  const { type, fieldId, fieldConfig } = column
  const cloneConfig = _.cloneDeep(fieldConfig)
  const options = _.get(cloneConfig, 'property.options')

  let childNode = children
  switch (type) {
    case NumFieldType.SingleText:
    case NumFieldType.DateTime:
    case NumFieldType.Number:
    case NumFieldType.Email:
    case NumFieldType.Phone:
      break

    case NumFieldType.Text:
      childNode = <MultipleText value={record[fieldId]} />
      break

    case NumFieldType.OptionStatus:
      childNode = (
        <SingleSelect value={record[fieldId]} fieldConfig={fieldConfig} />
      )
      break
    // 面试状态
    case NumFieldType.InterviewStatus:
      if (options) {
        _.set(cloneConfig, 'property.options', flatList(options))
      }
      childNode = (
        <SingleSelect value={record[fieldId]} fieldConfig={cloneConfig} />
      )
      break

    // 邀请状态
    case NumFieldType.InviteStatus:
      if (options) {
        _.set(cloneConfig, 'property.options', flatList(options))
      }
      childNode = (
        <InviteSingleSelect
          value={record[fieldId]}
          fieldConfig={cloneConfig}
          record={record}
        />
      )
      break

    case NumFieldType.Attachment:
      childNode = <Attachment value={record[fieldId]} />
      break

    case NumFieldType.SingleSelect:
      childNode = (
        <SingleSelect value={record[fieldId]} fieldConfig={fieldConfig} />
      )
      break

    case NumFieldType.MultiSelect:
      childNode = (
        <MultiSelect value={record[fieldId]} fieldConfig={fieldConfig} />
      )
      break

    case NumFieldType.Link:
      childNode = <NetAddress value={record[fieldId]} record={record} />
      break

    case NumFieldType.Member:
      childNode = <MemberSelect value={record[fieldId]} userList={userList} />
      break

    case NumFieldType.Interviewer:
      childNode = <MemberSelect value={record[fieldId]} userList={userList} />
      break

    case NumFieldType.discuss:
      childNode = (
        <DiscussModalWrap
          fieldId={fieldId}
          record={record}
          reader={reader}
          writer={writer}
          manager={manager}
        />
      )
      break

    default:
      childNode = <StringifyTextRender value={record[fieldId]} />
  }

  let styles: React.CSSProperties = {}
  if (cIndex === 0) {
    styles = { position: 'sticky', left: '32px' }
  }

  return (
    <td {...restProps} style={{ ...styles }} id={`cell-${rIndex}-${cIndex}`}>
      {childNode}
    </td>
  )
}

export default TableColumnRender

const copyInviteLink = async (record: FlowItemTableDataType) => {
  const dstColumns = store.getState().workflow.curTableColumn
  const curDstId = store.getState().workflow.curFlowDstId
  const user = store.getState().global.user

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
      store.dispatch(freshCurTableRows(curDstId!))
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

const SingleText: React.FC<{ value: any; children?: React.ReactNode }> = ({
  value,
}) => {
  if (_.isArray(value)) {
    return (
      <div>
        {value &&
          value.map &&
          value.map((item: any, i: number) => <div key={i}>{item.text}</div>)}
      </div>
    )
  } else {
    return <div>{value}</div>
  }
}

const MultipleText: React.FC<{ value: any; children?: React.ReactNode }> = ({
  value,
}) => {
  return (
    <div>
      {value && value.map && value.map((item: any) => <div>{item.text}</div>)}
    </div>
  )
}

const getStatusText = (value: any, fieldConfig: any) => {
  const temp = _.get(fieldConfig, 'property.options') || []
  if (temp.length === 0) {
    return
  }

  const item0 = temp[0]
  let options: any = []

  if (typeof item0 === 'string') {
    options = temp.map((item: any) => ({
      label: item,
      value: item,
    }))
  }

  if (typeof item0 === 'object') {
    options = temp.map((item: any) => ({
      label: item.name,
      value: item.id,
    }))
  }

  return _.find(options, { value: value })?.label || ''
}

const SingleSelect: React.FC<{
  value: any
  fieldConfig: any
  children?: React.ReactNode
}> = ({ value, fieldConfig }) => {
  const text = getStatusText(value, fieldConfig)
  return text && text !== '' ? <Tag color="default">{text}</Tag> : <div></div>
}

const InviteSingleSelect: React.FC<{
  value: any
  fieldConfig: any
  record: any
  children?: React.ReactNode
}> = ({ value, fieldConfig, record }) => {
  const temp = _.get(fieldConfig, 'property.options') || []
  // console.log(111, record)
  if (temp.length === 0) {
    return <div></div>
  }

  const item0 = temp[0]
  let options: any = []

  if (typeof item0 === 'string') {
    options = temp.map((item: any) => ({
      label: item,
      value: item,
    }))
  }

  if (typeof item0 === 'object') {
    options = temp.map((item: any) => ({
      label: item.name,
      value: item.name,
    }))
  }

  const text = _.find(options, { value: value })?.label || ''
  if (text) {
    let color = 'default'
    if (text === '已同意') {
      color = '#87d068'
    }
    if (text === '已拒绝') {
      color = '#f50'
    }
    return (
      <div>
        <Tag color={color}>{text}</Tag>
        {text == '未邀请' && (
          <Tag
            icon={<SendOutlined />}
            style={{ cursor: 'pointer !important' }}
            color="#55acee"
            onClick={() => {
              copyInviteLink(record)
            }}
          >
            生成邀约
          </Tag>
        )}
        {['未接受', '已同意', '已拒绝'].includes(text) && (
          <Tag
            icon={<CopyOutlined />}
            style={{ cursor: 'pointer !important' }}
            color="#55acee"
            onClick={() => {
              copyInviteLink(record)
            }}
          >
            复制
          </Tag>
        )}
      </div>
    )
  } else {
    return <div></div>
  }
}

const MultiSelect: React.FC<{
  value: any
  fieldConfig: any
  children?: React.ReactNode
}> = ({ value, fieldConfig }) => {
  const list = _.get(fieldConfig, 'property.options') || []
  const options = list.map((item: any) => ({
    label: item.name,
    value: item.id,
  }))

  return (
    <div>
      {value &&
        value.map &&
        value.map((item: any) => <Tag color="cyan">{item}</Tag>)}
    </div>
  )
}

const getFileName = (url: string) => {
  const file = url.split('/').pop()
  const fileName = file?.split('-')[1] || ''
  return fileName
}

const Attachment: React.FC<{
  value: any
  children?: React.ReactNode
}> = ({ value }) => {
  if (value) {
    const suffix = value.substring(value.lastIndexOf('.') + 1).toLowerCase()

    if (suffix === 'pdf' || suffix === 'docx' || suffix === 'doc') {
      return (
        <Link target="_blank" to={`/preview?url=${value}`} rel="noreferrer">
          {getFileName(value)}
        </Link>
      )
    } else {
      return (
        <a href={value} target="blank">
          {getFileName(value)}
        </a>
      )
    }
  } else {
    return <></>
  }
}

const SingleNumber: React.FC<{
  value: any
  children?: React.ReactNode
}> = ({ value }) => {
  return <div> {(value && value) || '未输入'}</div>
}

const SingleDateTime: React.FC<{
  value: any
  children?: React.ReactNode
}> = ({ value }) => {
  return <div> {(value && value) || '未输入'}</div>
}
const NetAddress: React.FC<{
  value: any
  record: any
  children?: React.ReactNode
}> = ({ value, record }) => {
  return (
    <div key={record.key}>
      {value && (
        <a href={`http://${value}`} target="_blank" rel="noreferrer">
          {value}
        </a>
      )}
    </div>
  )
}

const getMemberList = (value: any, userList: any) => {
  if (
    typeof value === `undefined` ||
    typeof userList === `undefined` ||
    !(value instanceof Array)
  ) {
    return
  }

  return value.map((item: string) => {
    return userList.filter((m: any) => m.id === item)[0]
  })
}

const MemberSelect: React.FC<{
  value: any
  userList: any
  children?: React.ReactNode
}> = ({ value, userList }) => {
  const memberList = getMemberList(value, userList)

  return (
    <div>
      {memberList &&
        memberList.map(
          (member: any) =>
            member && (
              <Tag
                key={member.id}
                color="blue"
                icon={<Avatar src={member.avatar} />}
              >
                {member.nickname}
              </Tag>
            )
        )}
    </div>
  )
}

const DiscussModalWrap: React.FC<{
  fieldId: string
  record: any
  reader: boolean
  writer: boolean
  manager: boolean
  children?: React.ReactNode
}> = ({ fieldId, record, reader, writer, manager }) => {
  const [open, setOpen] = React.useState<boolean>(false)
  return (
    <div>
      <Button type="text" onClick={() => setOpen(true)}>
        查看评论
      </Button>

      <DiscussModal
        fieldId={fieldId}
        record={record}
        open={open}
        close={() => setOpen(false)}
        reader={reader}
        writer={writer}
        manager={manager}
      />
    </div>
  )
}

const StringifyTextRender: React.FC<{
  value: any
  children?: React.ReactNode
}> = ({ value }) => {
  return <div>{JSON.stringify(value)}</div>
}
