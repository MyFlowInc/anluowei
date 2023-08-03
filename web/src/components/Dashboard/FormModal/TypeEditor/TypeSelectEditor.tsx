/**
 * type=3 
 */

import React, { useState, useRef, useEffect } from 'react'
import { PlusOutlined } from '@ant-design/icons'
import { Divider, Input, Select, Space, Button } from 'antd'
import type { InputRef } from 'antd'
import { ReverSedNumFieldType } from '../../TableColumnRender'
import {
  TableColumnItem,
  freshCurMetaData,
} from '../../../../store/workflowSlice'
import {
  UpdateDSMetaParams,
  updateDSMeta,
} from '../../../../api/apitable/ds-meta'
import { useAppDispatch } from '../../../../store/hooks'
import _ from 'lodash'

interface TypeSelectEditorProps {
  mode?: 'multiple'
  cell: TableColumnItem
  form:any 
  setForm: any
}

let index = 0
const TypeSelectEditor: React.FC<TypeSelectEditorProps> = (
  props: TypeSelectEditorProps
) => {
  const { mode, cell,form, setForm } = props


  const [items, setItems] = useState<string[]>([])
  const [name, setName] = useState('')

  const [value, setValue] = useState<string[] | string>([])

  const inputRef = useRef<InputRef>(null)
  const dispatch = useAppDispatch()

  const onNameChange = (event: React.ChangeEvent<HTMLInputElement>) => {
    setName(event.target.value)
  }

  const addItem = async (
    e: React.MouseEvent<HTMLButtonElement | HTMLAnchorElement>
  ) => {
    e.preventDefault()
    const text = name || `选项${index++}`
    const options = [...items, text]
    try {
      await updateField(options)
      setItems(options)
      setName('')
      setTimeout(() => {
        inputRef.current?.focus()
      }, 0)
    } catch (error) {}
  }

  // 更新字段值
  const updateField = async (options: string[]) => {
    const k =
      ReverSedNumFieldType[
        cell.type as unknown as keyof typeof ReverSedNumFieldType
      ] || 'NotSupport'
    const dstId = cell.dstId
    const temp: UpdateDSMetaParams = {
      dstId: cell.dstId,
      fieldId: cell.fieldId,
      name: cell.name,
      type: k,
      property: {
        options: options,
      },
    }
    await updateDSMeta(temp)
    await dispatch(freshCurMetaData(dstId))
  }
  // 初始化
  useEffect(() => {
    // console.log('useEffect--TypeSelectEditor === ', cell, 'form == =',form)
    const options = _.get(cell, 'fieldConfig.property.options')
    if(options) {
      setItems(options)
    }
    const temp = _.get(form, cell.fieldId) 
    if(!temp){
      mode === 'multiple' ? setValue([]) : setValue('')
    } else {
      setValue(temp)
    }
  }, [form])

  const handleSelectChange = (value: string[] | string) => {
    setValue(value)
    console.log('handleSelectChange--value === ', value)

    setForm({
      ...form,
      [cell.fieldId]: value,
    })
    console.log('onChangeContent', form)
  }

 
  
  return (
    <Select
      style={{ width: '100%' }}
      placeholder={mode === 'multiple' ? '多选框' : '单选框'}
      mode={mode}
      value={value}
      onChange={handleSelectChange}
      dropdownRender={(menu) => (
        <>
          {menu}
          <Divider style={{ margin: '8px 0' }} />
          <Space style={{ padding: '0 8px 4px' }}>
            <Input
              placeholder="Please enter item"
              ref={inputRef}
              value={name}
              onChange={onNameChange}
            />
            <Button type="text" icon={<PlusOutlined />} onClick={addItem}>
              添加项
            </Button>
          </Space>
        </>
      )}
      options={items.map((item) => ({ label: item, value: item }))}
    />
  )
}

export default TypeSelectEditor
