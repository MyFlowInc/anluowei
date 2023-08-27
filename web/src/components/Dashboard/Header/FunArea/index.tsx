import React from 'react'
import { Space } from 'antd'

import { useAppSelector } from '../../../../store/hooks'
import {
  selectCurTableColumn,
  selectCurTableRows,
} from '../../../../store/workflowSlice'

import Filter from './Filter'
import Sort from './Sort'
import Senior from './Senior'
import Search from './Search'

interface FunAreaProps {
  children?: React.ReactNode
}

const FunArea: React.FC<FunAreaProps> = () => {
  const dstColumns = useAppSelector(selectCurTableColumn)
  const records = useAppSelector(selectCurTableRows)

  return (
    <Space>
      <Filter records={records} columns={dstColumns} />
      <Sort />
      {/* <Senior /> */}
      <Search records={records} columns={dstColumns} />
    </Space>
  )
}

export default FunArea
