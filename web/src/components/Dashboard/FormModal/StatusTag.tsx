import { Dropdown, MenuProps, Tag } from 'antd'
import React, { useEffect, useState } from 'react'
import styled from 'styled-components'
import { WorkFlowStatusInfo, selectCurStatusFieldId, selectCurTableColumn } from '../../../store/workflowSlice'
import _ from 'lodash'
import { useAppSelector } from '../../../store/hooks'

const StatusTagRoot = styled.div``
const genItems = (list: WorkFlowStatusInfo[]): MenuProps['items'] => {
  return list.map((item) => {
    return {
      key: item.id || '',  // TODO: ts类型体操 没处理好这个类型
      label: <div>{item.name}</div>,
    }
  })
}

interface StatusTagProps {
  statusList: WorkFlowStatusInfo[]
  form: any
  setForm: (value: any) => void
}
export const StatusTag: React.FC<StatusTagProps> = (props) => {

  const { statusList, form, setForm } = props
  const [name, setName] = useState('未选择')
  const  curStatusFieldId = useAppSelector(selectCurStatusFieldId)

  useEffect(() => {
    const optionId = form[curStatusFieldId]
    if(optionId){
      setName(_.find(statusList, {id: optionId})?.name || '未选择')
    } else {
      setName('未选择')
    }
  }, [form])


  const handleMenuClick = (info: { key: string }) => {
    const id = info.key
    setForm({
      ...form,
      [curStatusFieldId]: id,
    })
  }

  return (
    <StatusTagRoot>
      <Dropdown
        menu={{
          items: genItems(statusList),
          onClick: (info: { key: string }) => {
            handleMenuClick(info)
          },
        }}
        placement="bottom"
      >
        <Tag color="#2db7f5" key={1}>{name}</Tag>
      </Dropdown>
    </StatusTagRoot>
  )
}
