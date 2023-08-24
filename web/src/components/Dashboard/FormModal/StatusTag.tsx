import { Dropdown, MenuProps, Tag } from 'antd'
import React, { useEffect, useState } from 'react'
import styled from 'styled-components'
import {
  WorkFlowStatusInfo,
  flatList,
  selectCurStatusFieldId,
  selectCurTableColumn,
} from '../../../store/workflowSlice'
import _ from 'lodash'
import { useAppSelector } from '../../../store/hooks'
import { ArrowLeftOutlined, ArrowRightOutlined } from '@ant-design/icons'

const StatusTagRoot = styled.div``

const genItems = (list: WorkFlowStatusInfo[]): MenuProps['items'] => {
  return list.map((item) => {
    return {
      key: item.id || '', // TODO: ts类型体操 没处理好这个类型
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
  const curStatusFieldId = useAppSelector(selectCurStatusFieldId)
  const [showLeft, setShowLeft] = useState(false)
  const [showRight, setShowRight] = useState(false)
  const [flatStatusList, setFlatStatusList] = useState<WorkFlowStatusInfo[]>(
    flatList(statusList)
  )
  useEffect(() => {
    setFlatStatusList(flatList(statusList))
  }, [statusList])

  useEffect(() => {
    const optionId = form[curStatusFieldId]
    updateArrowStatus(optionId)
    if (optionId) {
      setName(_.find(flatStatusList, { id: optionId })?.name || '未选择')
    } else {
      setName(flatStatusList[0]?.name)
      handleMenuClick({ key: flatStatusList[0]?.id || '' })
    }
  }, [form])

  const updateArrowStatus = (optionId: string) => {
    const index = _.findIndex(flatStatusList, { id: optionId })
    if (index >= 0 && index < flatStatusList.length - 1) {
      setShowRight(true)
    } else {
      setShowRight(false)
    }
    if (index > 0 && index < flatStatusList.length) {
      setShowLeft(true)
    } else {
      setShowLeft(false)
    }
  }

  const handleMenuClick = (info: { key: string }) => {
    const id = info.key
    if (!id) return
    setForm({
      ...form,
      [curStatusFieldId]: id,
    })
  }
  const leftHandler = () => {
    const optionId = form[curStatusFieldId]
    const index = _.findIndex(flatStatusList, { id: optionId })
    const id = flatStatusList[index - 1]?.id
    if (!id) return
    setForm({
      ...form,
      [curStatusFieldId]: id,
    })
  }
  const rightHandler = () => {
    const optionId = form[curStatusFieldId]
    const index = _.findIndex(flatStatusList, { id: optionId })
    const id = flatStatusList[index + 1]?.id
    if (!id) return
    setForm({
      ...form,
      [curStatusFieldId]: id,
    })
  }
  return (
    <StatusTagRoot>
      <div>
        {showLeft && (
          <ArrowLeftOutlined
            style={{ marginRight: '8px' }}
            onClick={leftHandler}
          />
        )}
        <Dropdown
          menu={{
            items: genItems(flatStatusList),
            onClick: (info: { key: string }) => {
              handleMenuClick(info)
            },
          }}
          placement="bottom"
        >
          <Tag color="#2db7f5" key={1}>
            {name}
          </Tag>
        </Dropdown>
        {showRight && <ArrowRightOutlined onClick={rightHandler} />}
      </div>
    </StatusTagRoot>
  )
}
