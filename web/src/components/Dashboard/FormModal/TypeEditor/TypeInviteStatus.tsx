/**
 * type=3
 */

import React, { useState, useEffect } from 'react'
import { Tag } from 'antd'
import { TableColumnItem } from '../../../../store/workflowSlice'

import _ from 'lodash'
import { CopyOutlined, SendOutlined } from '@ant-design/icons'
import { copyInviteLink } from '../../TableColumnRender'
import { FlowItemTableDataType } from '../../FlowTable/core'

interface TypeInviteStatusProps {
  cell: TableColumnItem
  form: any
  modalType: string
  setForm: any
  record: FlowItemTableDataType
}

let index = 0
const TypeInviteStatus: React.FC<TypeInviteStatusProps> = (
  props: TypeInviteStatusProps
) => {
  const { cell, form, setForm, record, modalType } = props

  const [items, setItems] = useState<string[]>([])
  const [value, setValue] = useState<string>('')

  // 初始化
  useEffect(() => {
    // console.log('useEffect--TypeInviteStatus === ', cell, 'form == =', form)
    const options = _.get(cell, 'fieldConfig.property.options')
    if (options) {
      setItems(options)
    }
    const temp =
      _.get(form, cell.fieldId) || _.get(cell, 'fieldConfig.defaultValue')
    if (!temp) {
      setValue('')
    } else {
      setValue(temp)
      handleSelectChange(temp)
    }
  }, [form[cell.fieldId]])

  const handleSelectChange = (v: string) => {
    if (v == value) {
      return
    }
    setValue(v)
    console.log('handleSelectChange--value === ', v)
    setForm({
      ...form,
      [cell.fieldId]: v,
    })
    console.log('onChangeContent', form)
  }

  if (value) {
    let color = 'default'
    if (value === '已同意') {
      color = '#87d068'
    }
    if (value === '已拒绝') {
      color = '#f50'
    }
    if (modalType === 'add') {
      return <Tag color={color}>{value}</Tag>
    }
    return (
      <div>
        <Tag color={color}>{value}</Tag>
        {value == '未邀请' && (
          <Tag
            icon={<SendOutlined />}
            style={{ cursor: 'pointer' }}
            color="#55acee"
            onClick={() => {
              handleSelectChange('未接受')
              copyInviteLink(record)
            }}
          >
            生成邀约
          </Tag>
        )}
        {['未接受', '已同意', '已拒绝'].includes(value) && (
          <Tag
            icon={<CopyOutlined />}
            style={{ cursor: 'pointer' }}
            color="#55acee"
            onClick={() => {
              copyInviteLink(record, { stop: 1 })
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

  // return (
  //   <>
  //     <TreeSelect
  //       fieldNames={{
  //         label: 'name',
  //         value: 'name',
  //         children: 'children',
  //       }}
  //       style={{ width: '100%' }}
  //       value={value}
  //       dropdownStyle={{ maxHeight: 400, overflow: 'auto' }}
  //       placeholder="Please select"
  //       allowClear
  //       treeDefaultExpandAll
  //       onChange={handleSelectChange}
  //       treeData={items as any}
  //     />
  //   </>
  // )
}

export default TypeInviteStatus
