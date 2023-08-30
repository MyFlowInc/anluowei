/**
 * type=3
 */

import React, { useState, useEffect } from 'react'
import { Tag } from 'antd'
import { TableColumnItem } from '../../../../store/workflowSlice'

import _ from 'lodash'

interface TypeTreeSelectProps {
  cell: TableColumnItem
  form: any
  setForm: any
}

let index = 0
const TypeTreeSelect: React.FC<TypeTreeSelectProps> = (
  props: TypeTreeSelectProps
) => {
  const { cell, form, setForm } = props

  const [items, setItems] = useState<string[]>([])
  const [value, setValue] = useState<string>('')

  // 初始化
  useEffect(() => {
    console.log('useEffect--TypeTreeSelect === ', cell, 'form == =', form)
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
    return <Tag color={color}>{value}</Tag>
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

export default TypeTreeSelect
