import { SyntheticEvent, useEffect, useState, useRef } from 'react'
import { Input } from 'antd'
import { WorkFlowFieldInfo } from '../../../store/workflowSlice'
import styled from 'styled-components'
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

import { NumFieldType } from '../TableColumnRender'
import _ from 'lodash'
import TypeSelectEditor from './TypeEditor/TypeSelectEditor'
import TypeAttachment from './TypeEditor/TypeAttachment'
import TypeSingleText from './TypeEditor/TypeSingleText'
import TypeNumber from './TypeEditor/TypeNumber'
import TypeDateTime from './TypeEditor/TypeDateTime'
import TypeLink from './TypeEditor/TypeLink'
import TypeEmail from './TypeEditor/TypeEmail'
import TypePhone from './TypeEditor/TypePhone'
import TypeMember from './TypeEditor/TypeMember'
import TypeDiscuss from './TypeEditor/TypeDiscuss'
import TypeTreeSelect from './TypeEditor/TypeTreeSelect'

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
    key: 'NotSupport',
    label: '未识别类型',
    type: NumFieldType.NotSupport,
    icon: <img src={errorSvg} width={12} height={12} />,
  },
]

const CellEditorItemRoot = styled.div`
  flex: 1;
`;
interface CellEditorItemProps {
  item: WorkFlowFieldInfo
  form: { [id: string]: string }
  setForm: (value: any) => void
  modalType: string
}
const CellEditorItem: React.FC<CellEditorItemProps> = (props) => {
  const { item, form, setForm, modalType } = props

  return (
    <CellEditorItemRoot>
      {item.type === NumFieldType.SingleText && (
        <TypeSingleText cell={item} {...{ form, setForm }} />
      )}
      {item.type === NumFieldType.Number && (
        <TypeNumber cell={item} {...{ form, setForm }} />
      )}
      {item.type === NumFieldType.SingleSelect && (
        <TypeSelectEditor cell={item} {...{ form, setForm }} />
      )}
      {item.type === NumFieldType.MultiSelect && (
        <TypeSelectEditor mode="multiple" cell={item} {...{ form, setForm }} />
      )}
      {item.type === NumFieldType.Attachment && (
        <TypeAttachment mode="multiple" cell={item} {...{ form, setForm }} />
      )}
      {item.type === NumFieldType.DateTime && (
        <TypeDateTime cell={item} {...{ form, setForm }} />
      )}
      {item.type === NumFieldType.Member && (
        <TypeMember mode="multiple" cell={item} {...{ form, setForm }} />
      )}
      {item.type === NumFieldType.Link && (
        <TypeLink cell={item} {...{ form, setForm }} />
      )}
      {item.type === NumFieldType.Email && (
        <TypeEmail cell={item} {...{ form, setForm }} />
      )}
      {item.type === NumFieldType.Phone && (
        <TypePhone cell={item} {...{ form, setForm }} />
      )}
      {item.type === NumFieldType.discuss && (
        <TypeDiscuss cell={item} {...{ form, setForm }} />
      )}
      {item.type === NumFieldType.InviteStatus && (
        <TypeTreeSelect cell={item} {...{ form, setForm }} />
      )}
    </CellEditorItemRoot>
  )
}
export default CellEditorItem
