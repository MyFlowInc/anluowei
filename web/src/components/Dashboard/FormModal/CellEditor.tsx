import moveSvg from '../assets/move.svg'
import { SyntheticEvent, useEffect, useState, useRef } from 'react'
import { Dropdown, Input, MenuProps, Space, Tooltip, Form } from 'antd'
import { WorkFlowFieldInfo } from '../../../store/workflowSlice'
import styled from 'styled-components'
import {
  CheckOutlined,
  CloseOutlined,
  MoreOutlined,
  EditOutlined,
} from '@ant-design/icons'
import dateSvg from '../assets/type/date.svg'
import labelSvg from '../assets/type/label.svg'
import linkSvg from '../assets/type/link.svg'
import mailSvg from '../assets/type/mail.svg'
import mediaSvg from '../assets/type/media.svg'
import memberSvg from '../assets/type/member.svg'
import multiSvg from '../assets/type/multi-select.svg'
import numSvg from '../assets/type/num.svg'
import phoneSvg from '../assets/type/phone.svg'
import singleSvg from '../assets/type/single-select.svg'
import errorSvg from '../assets/type/error.svg'

import { NumFieldType, ReverSedNumFieldType } from '../TableColumnRender'
import _, { lowerCase } from 'lodash'
import { UpdateDSMetaParams } from '../../../api/apitable/ds-meta'
import CellEditorItem from './CellEditorItem'
import { FlowItemTableDataType } from '../FlowTable/core'
const FieldTypeList = [
  {
    key: 'SingleText',
    label: '文本',
    type: NumFieldType.SingleText,
    icon: <img src={labelSvg} width={12} height={12} />,
  },
  {
    key: 'Number',
    label: '数字',
    type: NumFieldType.Number,
    icon: <img src={numSvg} width={12} height={12} />,
  },
  {
    key: 'SingleSelect',
    label: '单选',
    type: NumFieldType.SingleSelect,
    icon: <img src={singleSvg} width={12} height={12} />,
  },
  {
    key: 'MultiSelect',
    label: '多选',
    type: NumFieldType.MultiSelect,
    icon: <img src={multiSvg} width={12} height={12} />,
  },
  {
    key: 'DateTime',
    label: '日期',
    type: NumFieldType.DateTime,
    icon: <img src={dateSvg} width={12} height={12} />,
  },
  {
    key: 'Member',
    label: '成员',
    type: NumFieldType.Member,
    icon: <img src={memberSvg} width={12} height={12} />,
  },
  {
    key: 'Attachment',
    label: '媒体或文件',
    type: NumFieldType.Attachment,
    icon: <img src={mediaSvg} width={12} height={12} />,
  },
  {
    key: 'Link',
    label: '网址链接',
    type: NumFieldType.Link,
    icon: <img src={linkSvg} width={12} height={12} />,
  },
  {
    key: 'Email',
    label: '邮箱',
    type: NumFieldType.Email,
    icon: <img src={mailSvg} width={12} height={12} />,
  },
  {
    key: 'Phone',
    label: '电话',
    type: NumFieldType.Phone,
    icon: <img src={phoneSvg} width={12} height={12} />,
  },
  {
    key: 'Discuss',
    label: '评论',
    type: NumFieldType.discuss,
    icon: <EditOutlined style={{ fontSize: 12 }} />,
  },
  {
    key: 'NotSupport',
    label: '未识别类型',
    type: NumFieldType.NotSupport,
    icon: <img src={errorSvg} width={12} height={12} />,
  },
]

interface EditTitleProps {
  item: WorkFlowFieldInfo
  type: string
  setType: (type: 'view' | 'edit') => void
  updateField: (item: UpdateDSMetaParams, b?: boolean) => void
  deleteField: (item: WorkFlowFieldInfo) => void
}
const EditTitle: React.FC<EditTitleProps> = (props) => {
  const { item, type, setType, updateField, deleteField } = props
  const [name, setName] = useState(item.name)
  const onChange = (event: SyntheticEvent) => {
    const target = event.target as HTMLInputElement
    setName(target.value)
  }

  const changeFieldName = async () => {
    const k =
      ReverSedNumFieldType[
        item.type as unknown as keyof typeof ReverSedNumFieldType
      ] || 'NotSupport'

    const temp: UpdateDSMetaParams = {
      dstId: item.dstId,
      fieldId: item.fieldId,
      name: name,
      type: k,
    }
    await updateField(temp)
    setType('view')
  }

  const cancelBubble = (e: SyntheticEvent) => {
    e.stopPropagation()
  }
  const items: MenuProps['items'] = [
    {
      key: 'edit',
      label: '修改',
    },
    {
      key: 'delete',
      label: '删除',
    },
    {
      key: 'type',
      label: '类型',
      children: FieldTypeList,
    },
  ]
  const handleMenuClick = async (
    info: { key: string; keyPath: string[] },
    item: WorkFlowFieldInfo
  ) => {
    if (info.key === 'edit') {
      return setType('edit')
    }
    if (info.key === 'delete') {
      return deleteField(item)
    }
    if (info.keyPath.length > 1) {
      const type = _.find(FieldTypeList, { key: info.key })?.type || ''

      if (type) {
        const k =
          ReverSedNumFieldType[
            type as unknown as keyof typeof ReverSedNumFieldType
          ] || 'NotSupport'
        const temp: UpdateDSMetaParams = {
          dstId: item.dstId,
          fieldId: item.fieldId,
          name: name, //
          type: k,
        }
        await updateField(temp, true)
        setType('view')
        return
      }
    }
  }
  const TitleView = (type: string) => {
    if (type === 'edit') {
      return (
        <Input
          placeholder="请输入"
          defaultValue={item.name}
          onChange={onChange}
          onClick={cancelBubble}
          className="text input"
        />
      )
    }
    return (
      <Tooltip title={item.name}>
        <div className="text"> {item.name}</div>
      </Tooltip>
    )
  }
  const confirmView = (type: string) => {
    if (type === 'edit') {
      return (
        <>
          <CheckOutlined
            className="more"
            onClick={() => {
              changeFieldName()
            }}
          />
          <CloseOutlined
            className="more"
            onClick={() => {
              setType('view')
            }}
          />
        </>
      )
    }
    const selectKey =
      ReverSedNumFieldType[
        item.type as unknown as keyof typeof ReverSedNumFieldType
      ]

    return (
      <Dropdown
        menu={{
          items,
          onClick: (info: { key: string; keyPath: string[] }) => {
            handleMenuClick(info, item)
          },
          selectable: true,
          defaultSelectedKeys: [selectKey],
        }}
      >
        <a onClick={(e) => e.preventDefault()}>
          <Space>
            <MoreOutlined style={{ color: 'black' }} />
          </Space>
        </a>
      </Dropdown>
    )
  }

  return (
    <div className="item-title">
      {TitleView(type)}
      {confirmView(type)}
    </div>
  )
}

const CellEditorLabel = styled.div`
  cursor: default;
  display: flex;
  width: 100%;
  align-items: center;
  justify-content: space-between;

  .item-move {
    width: 16px;
    height: 16px;
    cursor: grab;
  }
  .item-title {
    margin-left: 16px;
    display: flex;
    align-items: center;
    width: 130px;
    .text {
      width: 130px;
      overflow: hidden; //超出的文本隐藏
      text-overflow: ellipsis; //溢出用省略号显示
      white-space: nowrap; //溢出不换行
      text-align: left;
    }
    .input {
      width: 122px;
      margin-right: 8px;
    }
    .more {
      margin-left: 4px;
    }
  }
  .flow-item-edit {
    flex: 1;
    /* width: 80%; */
  }
`

interface CellEditorProps {
  item: WorkFlowFieldInfo
  form: { [id: string]: string }
  setForm: (value: any) => void
  updateField: (item: UpdateDSMetaParams, b?: boolean) => void
  deleteField: (item: WorkFlowFieldInfo) => void
  modalType: string
  record: FlowItemTableDataType
}
const CellEditor: React.FC<CellEditorProps> = (props) => {
  const { item, updateField, deleteField, form, setForm, modalType, record } =
    props
  const [type, setType] = useState('view')
  let rules: any = undefined

  switch (item.type) {
    case NumFieldType.InterviewStatus:
      return <div className="hidden"></div>
    case NumFieldType.SingleText:
      const isRequired = _.get(item, 'fieldConfig.property.isRequired')
      if (typeof isRequired !== `undefined` && isRequired) {
        rules = [{ required: true, message: `请输入${item.name}!` }]
      }
      break
    case NumFieldType.Email:
      rules = [{ type: 'email', message: '请输入有效的邮箱地址.' }]
      break
    case NumFieldType.Phone:
      rules = [
        {
          pattern:
            /^(13[0-9]|14[01456879]|15[0-35-9]|16[2567]|17[0-8]|18[0-9]|19[0-35-9])\d{8}$/,
          message: '请输入有效的手机号码.',
        },
      ]
      break
    case NumFieldType.Member:
    case NumFieldType.Interviewer:
    case NumFieldType.MultiSelect:
      rules = [{ type: 'array' }]
      break
    case NumFieldType.Number:
      rules = [{ type: 'number' }]
      break

    default:
  }

  return (
    <Form.Item
      name={item.fieldId}
      label={
        <CellEditorLabel>
          <img src={moveSvg} className="item-move"></img>
          {EditTitle({ item, type, setType, updateField, deleteField })}
        </CellEditorLabel>
      }
      style={{ margin: '8px', padding: 0 }}
      rules={rules}
    >
      <CellEditorItem {...{ item, form, setForm, modalType, record }} />
    </Form.Item>
  )
}

export default CellEditor
